package com.ssh.action;

import com.ssh.domain.Dept;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Administrator on 2016/12/27.
 */
@Controller
@RequestMapping("/dept")
public class DeptController {

    @Autowired
    private SessionFactory sessionFactory;

    @RequestMapping("/deptDemo")
    public String deptDemo(Model model){
        System.out.println("deptDemo");
        Session session = sessionFactory.getCurrentSession();
        Transaction tx = session.getTransaction();
        Dept dept=null;
        try {
            tx.begin();
             dept= new Dept(3, "33", "333");
            session.persist(dept);
            model.addAttribute("dept",dept);
            tx.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            session.close();
        }
        return  "/aa.jsp";

    }
}
