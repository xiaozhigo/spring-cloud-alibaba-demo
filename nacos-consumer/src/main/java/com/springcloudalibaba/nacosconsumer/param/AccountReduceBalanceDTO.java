package com.springcloudalibaba.nacosconsumer.param;

import lombok.Data;

@Data
public class AccountReduceBalanceDTO {

    /**
     * 用户编号
     */
    private Long userId;

    /**
     * 扣减金额
     */
    private Integer price;

    public Long getUserId() {
        return userId;
    }

    // ... 省略 setter/getter 方法

}
