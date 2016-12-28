package com.ssh.dao;

import com.ssh.domain.Dept;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/12/28.
 */
public interface DeptDao {
    void insertDept(Dept dept);
    Dept selectDept(int deptno);

    List<Dept> selectAll();

    Dept selectById(Integer id);

    Serializable insert(Dept dept);
}
