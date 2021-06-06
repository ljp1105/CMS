//package com.xuecheng.test.rabbitmq.mq;
//
//
//import com.rabbitmq.client.Channel;
//import com.xuecheng.test.rabbitmq.config.RabbitmqConfig;
//import org.springframework.amqp.rabbit.annotation.RabbitHandler;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//import sun.plugin2.message.Message;
//
//
//@Component
//public class ReceiveHandler {
//    //监听email队列
//    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_EMAIL})
//    public void receive_email(String msg, Message message, Channel channel){
//        System.out.println(message);
//    }
//    //监听email队列
//    @RabbitListener(queues = {RabbitmqConfig.QUEUE_INFORM_SMS})
//    public void receive_sms(String msg,Message message,Channel channel){
//        System.out.println(msg); }
//
//}
