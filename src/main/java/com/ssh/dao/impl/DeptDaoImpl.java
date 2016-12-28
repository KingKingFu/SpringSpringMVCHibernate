package com.ssh.dao.impl;

import com.ssh.dao.DeptDao;
import com.ssh.domain.Dept;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
@Repository
@Transactional
public class DeptDaoImpl implements DeptDao {

    @Autowired
    private SessionFactory sessionFactory;

    private Session getSession(){
        return sessionFactory.openSession();
//        return sessionFactory.getCurrentSession();
    }

    @Override
    public void insertDept(Dept dept) {
        Session session = getSession();
        session.save(dept);
    }

    @Override
    public Dept selectDept(int deptno) {
        Session session = getSession();
        return session.get(Dept.class,deptno);
//        String hql="from Dept where deptno=:deptno";
//        return (Dept) getSession().createQuery(hql).setParameter("deptno",deptno).uniqueResult();
    }

    @Override
    public List<Dept> selectAll() {
        String hql = "from Dept";
        Query<Dept> query = getSession().createQuery(hql,Dept.class);
        return query.list();
    }

    @Override
    public Dept selectById(Integer id) {
        return getSession().get(Dept.class,id);
    }

    @Override
    public Serializable insert(Dept dept) {
        return getSession().save(dept);
    }
}
