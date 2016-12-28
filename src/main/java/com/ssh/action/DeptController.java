package com.ssh.action;

import com.ssh.domain.Dept;
import com.ssh.service.DeptService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/dept")
public class DeptController {

//    @Autowired
//    private SessionFactory sessionFactory;

    @Autowired
    private DeptService deptService;

    @RequestMapping("/add")
    public String add(Dept dept) {
        boolean res = deptService.addDept(dept);
        if (res)
            return "/dept/find.do";
        else
            return "/index.jsp";
    }

    @RequestMapping("/find")
    public String find(Model model) {
        model.addAttribute("depts", deptService.findAll());
        return "/deptShow.jsp";
    }


    @RequestMapping(value = "/deptInput",method = {RequestMethod.POST})
    public String deptInput(Model model,Dept dept){
        deptService.addDept(dept);
        model.addAttribute("dept",dept);
        return "/aa.jsp";
    }



    @RequestMapping(value = "/deptFind",method = {RequestMethod.POST})
    public String deptFind(Model model,int deptno){
        Dept dept = deptService.findDept(deptno);
        model.addAttribute("dept",dept);
        return "/aa.jsp";
    }



//    @RequestMapping("/deptDemo")
//    public String deptDemo(Model model){
//        System.out.println("deptDemo");
//        Session session = sessionFactory.getCurrentSession();
//        Transaction tx = session.getTransaction();
//        Dept dept=null;
//        try {
//            tx.begin();
//             dept= new Dept(3, "33", "333");
//            session.persist(dept);
//            model.addAttribute("dept",dept);
//            tx.commit();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }finally {
//            session.close();
//        }
//        return  "/aa.jsp";
//
//    }
}
