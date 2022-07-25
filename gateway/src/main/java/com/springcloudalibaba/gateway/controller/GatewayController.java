package com.springcloudalibaba.gateway.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wulei
 * @date 2022年07月25日 15:59
 */
@RestController
public class GatewayController {

    @RequestMapping("/gatewayTest")
    public String gatewayTest(){
        return "gatewayTest";
    }
}
