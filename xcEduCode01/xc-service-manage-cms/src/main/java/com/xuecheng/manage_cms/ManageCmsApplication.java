package com.xuecheng.manage_cms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;



@SpringBootApplication
//@EnableEurekaClient
@EntityScan("com.xuecheng.framework.domain.cms")
@ComponentScan(basePackages ="com.xuecheng.api")
@ComponentScan(basePackages = "com.xuecheng.manage_cms")
@ComponentScan(basePackages="com.xuecheng.framework")//扫描common工程下的类
@EnableSwagger2
@EnableScheduling
public class ManageCmsApplication {
    public static void main(String[] args) {
        SpringApplication.run(ManageCmsApplication.class);
    }
}
