package com.ssh.service;

import com.ssh.domain.Dept;

import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */

public interface DeptService {
    void addDept1(Dept dept);
    Dept findDept(int deptno);

    Dept findDeptById(Integer id);

    List<Dept> findAll();

    boolean addDept(Dept dept);
}
