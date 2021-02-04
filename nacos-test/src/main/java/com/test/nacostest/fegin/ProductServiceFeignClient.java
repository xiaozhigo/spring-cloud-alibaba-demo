package com.test.nacostest.fegin;

import com.test.nacostest.param.ProductReduceStockDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "nacos-provider")
public interface ProductServiceFeignClient {
    @PostMapping("/product/reduce-stock")
    void reduceStock(@RequestBody ProductReduceStockDTO productReduceStockDTO);
}
