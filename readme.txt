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
2.拷贝实体类(配置好注解信息)
@Entity     @Table(name="")    @Id     @Column(unique = true,length = 25))
@OneToMany(cascade = CascadeType.ALL,mappedBy = " ")
@NamedQuery(name = "queryAll",query="from  ")
@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
@JoinColumn(name = " ")
3.编写配置文件
    applicationContext.xml
        配置自动扫描、配置数据源、配置MyBatis的SqlSessionFactory、配置事务管理
    hibernate.cfg.xml(只写其中关于hibernate原生配置信息)
        数据库方言、是否显示生成的sql语句、格式化输出sql语句、自动根据映射文件更新数据库、配置currentSession上下文
    log4j.properties(查看输出的sql语句)
4.测试
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