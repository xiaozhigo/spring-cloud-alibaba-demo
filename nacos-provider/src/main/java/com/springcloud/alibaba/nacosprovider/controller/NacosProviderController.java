package com.springcloud.alibaba.nacosprovider.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NacosProviderController {

    @RequestMapping("/nacosTest")
    public String nacosTest(@RequestParam("name") String name){
        return name;
    }
}
