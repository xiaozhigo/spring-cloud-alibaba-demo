package com.test.nacostest.fegin;

// AccountServiceFeignClient.java

import com.test.nacostest.param.AccountReduceBalanceDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * `account-service` 服务的 Feign 客户端
 */
@FeignClient(name = "nacos-consumer")
public interface AccountServiceFeignClient {

    @PostMapping("/account/reduce-balance")
    void reduceBalance(@RequestBody AccountReduceBalanceDTO accountReduceBalanceDTO);

}
