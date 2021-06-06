package com.casic.hibernatePackage;


import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.Arrays;
import java.util.List;

public class HibernateTest {

    public static void main(String[] args) {
//        //加载核心配置文件
//        Configuration configure = new Configuration().configure("resource/hibernate.cfg.xml");
//        //手动加载配置文件
////        Configuration configuration = configure.addResource("resource/Custormer.hbm.xml");
//
//        //创建SessionFactory对象
//        SessionFactory sessionFactory = configure.buildSessionFactory();
//
//        //通过SessionFactory对象获取到session对象
//        Session session = sessionFactory.openSession();
//
//        Transaction transaction = session.beginTransaction();
//
//        //插入一条数据
//        Customer customer = new Customer();
//        customer.setCust_name("测试一条数据");
//        session.save(customer);
//
//        transaction.commit();
//
//        session.close();
//        sessionFactory.close();
//   saveOrUpdate();
        findAll();
    }

    public static void findAll(){
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
//        Customer customer = new Customer();
//        customer.setCust_id(3L);;
//        customer.setCust_name("aaaa");
//        session.saveOrUpdate(customer);
        SQLQuery query = session.createSQLQuery("select * from cst_customer");
        List<Object[]> list = query.list();
        for (Object[] obs : list) {
            System.out.println(Arrays.toString(obs));
        }
        transaction.commit();
        session.close();
    }
    public static void saveOrUpdate(){
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setCust_id(3L);;
        customer.setCust_name("aaaa");
        session.saveOrUpdate(customer);
        transaction.commit();
        session.close();
    }
    public static void delete(){
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setCust_id(1L);;
        customer.setCust_name("aaaa");
        session.delete(customer);
        transaction.commit();
        session.close();
    }
    public static void update(){
        Session session = HibernateUtils.openSession();
        Transaction transaction = session.beginTransaction();
        Customer customer = new Customer();
        customer.setCust_id(1L);;
        customer.setCust_name("aaaa");
        session.update(customer);
        transaction.commit();
        session.close();
    }
    public void query(){
        Session session = HibernateUtils.openSession();
//        Transaction transaction = session.beginTransaction();
        Customer customer = session.load(Customer.class, 1L);
//        System.out.println(customer);
        Customer customer1 = session.get(Customer.class, 1L);
        System.out.println(customer1);
    }
}
