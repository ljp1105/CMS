package com.xuecheng.test.rabbitmq.test;

import com.xuecheng.test.rabbitmq.config.RabbitmqConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Producer_topic_springbooot {

    @Autowired
    RabbitTemplate rabbitTemplate;


    @Test
    public void testSendByTopic(){
        for (int i = 0; i < 5; i++) {
            String message="ssm email inform to user"+i;
            rabbitTemplate.convertAndSend(RabbitmqConfig.EXCHANGE_TOPICS_INFORM,"inform.sms.email",message);
            System.out.println("Send Message is:"+message+"");
        }
    }
}
