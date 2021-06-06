package com.casic.hibernatePackage;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class HqlTest {
    public static void main(String[] args) {
        Session session = HibernateUtils.openSession();
//        Transaction transaction = session.getTransaction();
//        transaction.begin();
//        //刪除Customer
//        Query query = session.createQuery("delete from Customer");
//        int i = query.executeUpdate();
//        transaction.commit();
//        System.out.println(i);

        //1.查询所有记录

        Query query = session.createQuery("from Customer");
        Iterator iterate = query.iterate();
        while (iterate.hasNext()){
            Object next = iterate.next();
            System.out.println(next);
        }
        //2.条件查询
//        Query query = session.createQuery("from Customer where cust_name=?0");
//        query.setString(0,"aaaa");
//        List<Customer> list = query.list();
//        System.out.println(list);
        //3.条件查询
//        Query query = session.createQuery("from Customer where cust_id=:aa and cust_name=:bb");
//        query.setInteger("aa",2);
//        query.setString("bb","aaaa");
//        List<Customer> list = query.list();
//        System.out.println(list);
        //4.分页查询
//        Query query = session.createQuery("from Customer");
//        query.setFirstResult(0);
//        query.setMaxResults(3);
//
//        List<Customer> list = query.list();
//        System.out.println(list);
        //查询所有记录
//        Criteria criteria = session.createCriteria(Customer.class);
//        Object o = criteria.uniqueResult();
//        System.out.println(o);
        //2.条件查询
//        Criteria criteria = session.createCriteria(Customer.class);
//        criteria.add(Restrictions.eq("cust_name","aaaa"));
//        List<Customer> list = criteria.list();
//        System.out.println(list);
        //3.分页查询
//        Criteria criteria = session.createCriteria(Customer.class);
//        criteria.setFirstResult(3);
//        criteria.setMaxResults(3);
//        List<Customer> list = criteria.list();
//        System.out.println(list);
        //基本SQL语句查询
//        SQLQuery sqlQuery = session.createSQLQuery("select * from cst_customer");
////        List<Object[]> list = sqlQuery.list();
////        for (Object[] objects : list) {
////            System.out.println(Arrays.toString(objects));
////        }
//        //将查询结果封装到对象
//        sqlQuery.addEntity(Customer.class);
//        List<Customer> list = sqlQuery.list();
//        for (Customer customer : list) {
//            System.out.println(customer);
//        }
    }
}
