package com.xuecheng.manage_cms.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.xml.transform.Result;

@RestController
@RequestMapping("/cms/test")
public class TestController {
    @GetMapping("/testMethod")
    public void test(ModelAndView modelAndView,Persion persion){
        System.out.println("打印"+persion);
    }
}
