package com.casic.ProducerPackage;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer01 {
    //队列名称
    private static final String QUEUE = "helloworld";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        //rabbitMq默认虚拟机名称为“/"，虚拟机相当于一个独立的mq服务
        factory.setVirtualHost("/");


        //创建与RabbitMQ服务的TCP服务
        Connection connection = factory.newConnection();
        //创建与rabbitMq服务的TCP连接
        Channel channel = connection.createChannel();

        //声明队列，如果Rabbit中没有此队列将自动创建
        //队列名称、队列是否持久化、队列是否独占此连接、队列不再使用是否自动删除此队列、队列参数
        channel.queueDeclare(QUEUE,true,false,false,null);
        String message="helloword小明"+System.currentTimeMillis();
        //消息发布方法 Exchange名称、如果没有指定，则使用Default Exchange、
        // routingKey消息的路由key,是用于Exchage(交换机)将消息转发到指定的消息队列
        //消息包含属性
        //消息体
        channel.basicPublish("",QUEUE,null,message.getBytes());
        System.out.println("send message is"+message);
        channel.close();
        connection.close();
    }
}
