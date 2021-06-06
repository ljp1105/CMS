package com.xuecheng.manage_cms.conﬁg;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class TaskExecutorConfig {

    @Bean("taskExecutor")
    public Executor taskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(10); //设置线程池中初始化线程数量
        taskExecutor.setMaxPoolSize(20);//设置最大线程数量
        taskExecutor.setQueueCapacity(200);//设置等待队列大小
        taskExecutor.setKeepAliveSeconds(50); //等待线程
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return taskExecutor;
    }
}
