//package com.casic.Mybatis;
//
//public class MybatisObjectFactoryTest {
//    public static void main(String[] args) {
//        public static DataConnection dataConn = new DataConnection();
//
//            SqlSession sqlSession = dataConn.getSqlSession();
//            CartObjectFactory e = new CartObjectFactory();
//
//            List constructorArgTypes = new ArrayList();
//            constructorArgTypes.add(int.class);
//            constructorArgTypes.add(String.class);
//            constructorArgTypes.add(int.class);
//            constructorArgTypes.add(double.class);
//            constructorArgTypes.add(double.class);
//
//            List constructorArgs = new ArrayList();
//            constructorArgs.add(1);
//            constructorArgs.add("运动鞋");
//            constructorArgs.add(5);
//            constructorArgs.add(300);
//            constructorArgs.add(0.0);
//
//            ShoppingCart sCart = (ShoppingCart)e.create(ShoppingCart.class,constructorArgTypes,constructorArgs);
//            System.out.println(sCart.getTotalAmount());
//            sqlSession.close();
//    }
//}
