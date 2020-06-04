package com.lxh.transmigration;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FindFiction {

    @RequestMapping("/hello")
    public String sayHello(){
        return "qqqq";
    }
}
