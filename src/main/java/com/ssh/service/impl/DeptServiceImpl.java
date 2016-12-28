package com.ssh.service.impl;

import com.ssh.dao.DeptDao;
import com.ssh.domain.Dept;
import com.ssh.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Service
@Transactional
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;

    @Override
    public void addDept1(Dept dept) {
        deptDao.insertDept(dept);
    }

    @Override
    public Dept findDept(int deptno) {
        return deptDao.selectDept(deptno);
    }

    @Override
    public Dept findDeptById(Integer id) {
        return deptDao.selectById(id);
    }

    @Override
    public List<Dept> findAll() {
        return deptDao.selectAll();
    }

    @Override
    public boolean addDept(Dept dept) {
        Serializable serializable = deptDao.insert(dept);
        return serializable != null;
    }
}
