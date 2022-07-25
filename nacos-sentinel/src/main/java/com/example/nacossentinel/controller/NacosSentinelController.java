package com.example.nacossentinel.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wulei
 * @date 2022年07月22日 13:58
 */
@RestController
public class NacosSentinelController {

    @GetMapping("/testSentinel")
    @SentinelResource(value = "testSentinel_hot",blockHandler = "test_blockHandler",fallback = "test_fallback")
    public String testSentinel(@RequestParam("param") String param) throws Exception {
        if("23".equals(param)){
            throw new Exception("二哈");
        }
        return param;
    }

    public String test_blockHandler(String param, BlockException e){
        return "block:" + e.getMessage();
    }

    public String test_fallback(String param,Throwable throwable){
        return "fallback:" + throwable.getMessage();
    }

}
