package com.casic.Mybatis;

import com.casic.domain.Customer;
import com.casic.domain.ShoppingCart;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JdbcTest {
    public static void main(String[] args) throws IOException {
        String resource="mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        //加载配置文件并创建SqlSessionFactory
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        //创建SqlSession对象
        SqlSession sqlSession = sessionFactory.openSession();
        CartObjectFactory e = new CartObjectFactory();

        List constructorArgTypes = new ArrayList();
        constructorArgTypes.add(int.class);
        constructorArgTypes.add(String.class);
        constructorArgTypes.add(int.class);
        constructorArgTypes.add(double.class);
        constructorArgTypes.add(double.class);

        List constructorArgs = new ArrayList();
        constructorArgs.add(1);
        constructorArgs.add("运动鞋");
        constructorArgs.add(5);
        constructorArgs.add(300);
        constructorArgs.add(0.0);

        ShoppingCart sCart = (ShoppingCart)e.create(ShoppingCart.class,constructorArgTypes,constructorArgs);
        System.out.println(sCart.getTotalAmount());
        sqlSession.close();
//        List<Customer> customerList = sqlSession.selectList("com.casic.dao.CustomerMapper.selectAll");
//        System.out.println(customerList);
//        sqlSession.close();
    }
}
