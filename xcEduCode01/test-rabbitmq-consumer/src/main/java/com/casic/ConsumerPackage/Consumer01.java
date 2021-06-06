package com.casic.ConsumerPackage;

import com.rabbitmq.client.*;


import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer01 {
    private static final String QUEUE="helloworld";
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        //设置MabbitMQ所在服务器ip和端口
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明此队列
        //队列名称、队列是否持久化、队列是否独占此连接、队列不再使用是否自动删除此队列、队列参数
        channel.queueDeclare(QUEUE,true,false,false,null);
        DefaultConsumer consumer = new DefaultConsumer(channel) {

            /**
             *
             * @param consumerTag 消费者的标签，在chanel.basicConsume()去指定
             * @param envelope 消息包的内容，可以从中获取消息id,消息routingkey,交换机、消息和重传标志
             * @param properties
             * @param body
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //交换机
                java.lang.String exchange = envelope.getExchange();
                //路由key
                java.lang.String routingKey = envelope.getRoutingKey();
                //消息id
                long deliveryTag = envelope.getDeliveryTag();
                //消息内容
                String msg = new String(body,"utf-8");
                System.out.println("receive message:"+msg);
            }
        };
        //队列名称  是否自动回复 消费消息的方法
        channel.basicConsume(QUEUE,true,consumer);
    }
}
