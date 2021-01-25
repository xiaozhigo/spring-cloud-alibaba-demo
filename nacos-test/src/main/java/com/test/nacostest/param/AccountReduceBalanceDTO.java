package com.test.nacostest.param;

// AccountReduceBalanceDTO.java

import lombok.Data;

/**
 * 账户减少余额 DTO
 */
@Data
public class AccountReduceBalanceDTO {

    /** 用户编号 */
    private Long userId;

    /** 扣减金额 */
    private Integer price;

    // ... 省略 setter/getter 方法
}
