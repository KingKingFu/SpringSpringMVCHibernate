Spring + SpringMVC + Hibernate 整合步骤

1.拷贝jar文件配置信息
junit                         4.12
log4j                         1.2.17
mysql-connector-java          5.1.35
druid                         1.0.26               数据源
hibernate-core                5.2.5.Final
spring-context                4.3.4.RELEASE
spring-web                    4.3.4.RELEASE
aspectjweaver                 1.8.9
aspectjrt                     1.8.9
spring-jdbc                   4.3.4.RELEASE
spring-orm                    4.3.4.RELEASE
spring-webmvc                 4.3.4.RELEASE
jstl                          1.2
standard                       1.1.2
org.apache.commons.fileupload  1.2.2.LIFERAY-PATCHED-1  文件上传
org.apache.commons.io          2.4
fastjson                       1.2.21
拷贝实体类(配置好注解信息)
@Entity     @Table(name="")    @Id     @Column(unique = true,length = 25))
@OneToMany(cascade = CascadeType.ALL,mappedBy = " ")
@NamedQuery(name = "queryAll",query="from  ")
@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
@JoinColumn(name = " ")
### >2.编写配置文件 spring整合hibernate
    applicationContext.xml
        配置自动扫描、配置数据源、配置MyBatis的SqlSessionFactory、配置事务管理
    hibernate.cfg.xml(只写其中关于hibernate原生配置信息)
        数据库方言、是否显示生成的sql语句、格式化输出sql语句、自动根据映射文件更新数据库、配置currentSession上下文
    log4j.properties(查看输出的sql语句)

    1)spring 配置数据源
            <!--======================配置自动扫描=======================-->
            <context:component-scan base-package="com.ssh.service,com.ssh.dao"/>

        2)spring 配置自动扫描

            <!--======================配置数据源=======================-->
            <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
                <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql:///test2?useSSL=false"/>
                <property name="username" value="root"/>
                <property name="password" value="root"/>
            </bean>

        3)spring 配置hibernate的SessionFactory

            <bean id="sessionFactory" class="org.springframework.orm.hibernate5.LocalSessionFactoryBean">
                <property name="dataSource" ref="dataSource"/>
                <property name="configLocation" value="classpath:hibernate.cfg.xml"/>
                <property name="packagesToScan" value="com.ssh.domain"/>
            </bean>

        3)spring 配置hibernate的事务管理
             <bean id="txManager" class="org.springframework.orm.hibernate5.HibernateTransactionManager">
                    <property name="sessionFactory" ref="sessionFactory"/>
             </bean>
             <tx:annotation-driven transaction-manager="txManager"/>

### >3.spring整合web项目

      在web.xml中配置
        <!-- 加载Spring容器 -->
        <context-param>
          <param-name>contextConfigLocation</param-name>
          <param-value>classpath:applicationContext.xml</param-value>
        </context-param>
        <listener>
          <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
        </listener>
### >4.springMVC整合web项目

     1)在web.xml中配置
       <!--配置前端控制器-->
       <servlet>
         <servlet-name>SpringMVC1</servlet-name>
         <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
         <init-param>
           <param-name>contextConfigLocation</param-name>
           <param-value>classpath:springmvc.xml</param-value>
         </init-param>
       </servlet>
       <servlet-mapping>
         <servlet-name>SpringMVC1</servlet-name>
         <url-pattern>*.do</url-pattern>
       </servlet-mapping>

       <!--解决post中文乱码-->
       <filter>
         <filter-name>CharacterEncodingFilter</filter-name>
         <filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class>
         <init-param>
           <param-name>encoding</param-name>
           <param-value>utf-8</param-value>
         </init-param>
       </filter>
       <filter-mapping>
         <filter-name>CharacterEncodingFilter</filter-name>
         <url-pattern>/*</url-pattern>
       </filter-mapping>

     2)配置springMVC文件
         <!--=============自动扫描=================================-->
         <context:component-scan base-package="com.ssh.action"/>

         <!--============视图解析器 解析jsp 默认使用jstl标签=========-->
         <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
             <!--<property name="prefix" value="/WEB-INF/admin/"/>-->
             <!--<property name="suffix" value=".jsp"/>-->
         </bean>


         <!--==============基于注解配置映射器============================-->
         <mvc:annotation-driven conversion-service="customConversionService">
         </mvc:annotation-driven>

         <!-- ==============配置拦截器 =========================== -->
         <mvc:interceptors>
             <mvc:interceptor>
                 <mvc:mapping path="/action/**"/>
                 <bean class="com.ssh.interceptor.LogInterceptor"/>
             </mvc:interceptor>
         </mvc:interceptors>

         <!--=============自定义参数绑定============================-->
         <bean id="customConversionService"
               class="org.springframework.format.support.FormattingConversionServiceFactoryBean">
             <property name="converters">
                 <set>
                     <bean class="com.ssh.utils.String2DateTime"/>
                 </set>
             </property>
         </bean>

         <!--==============支持文件上传=============================-->
         <bean class="org.springframework.web.multipart.commons.CommonsMultipartResolver" id="multipartResolver">
             <property name="maxUploadSize" value="5242880"/>
         </bean>
***********************************************************************************************
测试
        生成表：
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
        SessionFactory sessionFactory = (SessionFactory) act.getBean("sqlSessionFactory");

        生成数据：
        Session session = sessionFactory.getCurrentSession();
                Transaction transaction = session.getTransaction();
                try {
                    transaction.begin();
                    //从主动方开始查，只查一次
                    Dept dept = new Dept(1, "1111111", "1111111");
                    session.persist(dept);
                    transaction.commit();
                } catch (Exception e) {
                    e.printStackTrace();
                    transaction.rollback();
                } finally {
                    session.close();
                }
            }