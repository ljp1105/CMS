package com.xuecheng.manage_cms.conﬁg;


import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Task {
    @Async("taskExecutor")
    public  void doTaskOne() throws InterruptedException {
        log.info("1");
        long start=System.currentTimeMillis();
        long end=System.currentTimeMillis();
        System.out.println("任务一耗时"+(end-start)+"毫秒");
    }
    @Async("taskExecutor")
    public void doTaskTwo() throws InterruptedException {
        log.info("2");
        long start=System.currentTimeMillis();
        long end=System.currentTimeMillis();
        System.out.println("任务二耗时"+(end-start)+"毫秒");
    }
    @Async("taskExecutor")
    public void doTaskThree() throws InterruptedException {
        log.info("3");
        long start=System.currentTimeMillis();
        long end=System.currentTimeMillis();
        System.out.println("任务三耗时"+(end-start)+"毫秒");
    }
}
