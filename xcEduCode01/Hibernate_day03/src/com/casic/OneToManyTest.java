//package com.casic;
//
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//public class OneToManyTest {
//    public static void main(String[] args) {
//        Session session = HibernateUtils.openSession();
//        Transaction tx = session.beginTransaction();
////
////        // 创建两个客户
////        Customer customer1 = new Customer();
////        customer1.setCust_name("王东");
////        Customer customer2 = new Customer();
////        customer2.setCust_name("赵洪");
//
//        // 创建三个联系人
//        LinkMan linkMan1 = new LinkMan();
//        linkMan1.setLkm_name("凤姐");
//        LinkMan linkMan2 = new LinkMan();
//        linkMan2.setLkm_name("如花");
//        LinkMan linkMan3 = new LinkMan();
//        linkMan3.setLkm_name("旺财");
//
//        // 设置关系:
//        linkMan1.setCustomer(customer1);
//        linkMan2.setCustomer(customer1);
//        linkMan3.setCustomer(customer2);
//        customer1.getLinkManSet().add(linkMan1);
//        customer1.getLinkManSet().add(linkMan2);
//        customer2.getLinkManSet().add(linkMan3);
//
//        // 保存数据:
//        session.save(customer1);
//        session.save(customer2);
//        session.save(linkMan1);
//        session.save(linkMan2);
//        session.save(linkMan3);
//
//
//        tx.commit();
//    }
//}
