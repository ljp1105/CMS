package com.casic.ProducerPackage;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer04_topics {
    //队列名称
    private static final String QUEUE_INFORM_EMAIL = "queue_inform_email";
    private static final String QUEUE_INFORM_SMS = "queue_inform_sms";
    private static final String EXCHANGE_FANOUT_INFORM="exchange_topic_inform";
    private static final String ROUTEKEY_EMAIL="inform.#.email.#";
    private static final String ROUTEKEY_SMS="inform.#.sms.#";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setVirtualHost("/");

        //创建一个连接
        Connection connection = factory.newConnection();
        //创建与交换机的通道，每个通道代表一个会话
        Channel channel = connection.createChannel();
        //声明交换机
        channel.exchangeDeclare(EXCHANGE_FANOUT_INFORM, BuiltinExchangeType.TOPIC);

        //声明通道
        channel.queueDeclare(QUEUE_INFORM_SMS,true,false,false,null);
        channel.queueDeclare(QUEUE_INFORM_EMAIL,true,false,false,null);
        channel.queueBind(QUEUE_INFORM_EMAIL,EXCHANGE_FANOUT_INFORM,ROUTEKEY_EMAIL);
        channel.queueBind(QUEUE_INFORM_EMAIL,EXCHANGE_FANOUT_INFORM,ROUTEKEY_SMS);

        //发送消息
        for (int i = 0; i < 10; i++) {
            String message = "inform to user"+i;
//            //发送邮件消息
//            channel.basicPublish(EXCHANGE_FANOUT_INFORM,"inform.email",null,message.getBytes());
//            System.out.println("Send Message is email:'" + message + "'");
            //发送短信消息
            channel.basicPublish(EXCHANGE_FANOUT_INFORM,QUEUE_INFORM_EMAIL,null,message.getBytes());
            System.out.println("Send Message is sms:'" + message + "'");
//            //发送邮件短信消息
//            channel.basicPublish(EXCHANGE_FANOUT_INFORM,"inform.email.sms",null,message.getBytes());
//            System.out.println("Send Message is sms and email:'" + message + "'");

        }
        channel.close();
        connection.close();
    }
}
