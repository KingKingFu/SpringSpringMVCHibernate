package com.ssh;

import com.ssh.domain.Dept;
import com.ssh.service.DeptService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2016/12/28.
 */
public class TT {
    @Test
    public void fun1() {
        ApplicationContext act = new ClassPathXmlApplicationContext("applicationContext.xml");
        DeptService deptService= (DeptService) act.getBean("deptServiceImpl");
        deptService.addDept(new Dept(9,"91","11"));
        deptService.findDept(1);
    }

    @Test
    public void fun2() {

    }
}
