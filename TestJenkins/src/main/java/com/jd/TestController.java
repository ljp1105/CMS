package com.jd;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Controller
public class TestController {



    @RequestMapping("/testMethod")
    public String testMethod(){
        return "1";
    }
}
