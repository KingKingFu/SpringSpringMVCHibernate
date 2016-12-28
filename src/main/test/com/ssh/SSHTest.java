package com.ssh;


import com.ssh.domain.Dept;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SSHTest {
    @Test
    public void fun1() {
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
        SessionFactory sessionFactory = (SessionFactory) act.getBean("sqlSessionFactory");

//        sessionFactory.openSession();
//        Session session=sessionFactory.getCurrentSession();

        Session session1 = sessionFactory.openSession();
        Session session2 = sessionFactory.openSession();
        System.out.println(session1 == session2);
    }


    @Test
    public void fun2() {
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
        SessionFactory sessionFactory = (SessionFactory) act.getBean("sqlSessionFactory");

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

    @Test
    public void fun3() {
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
        SessionFactory sessionFactory = (SessionFactory) act.getBean("sqlSessionFactory");

        Session session = sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        try {
            transaction.begin();
            //从主动方开始查，只查一次
            Dept dept = new Dept(2, "222", "22");
            session.persist(dept);
            transaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
            transaction.rollback();
        } finally {
            session.close();
        }
    }

}
