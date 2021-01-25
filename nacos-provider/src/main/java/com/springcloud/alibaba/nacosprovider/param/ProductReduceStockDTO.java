package com.springcloud.alibaba.nacosprovider.param;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 商品减少库存 DTO
 */
@Data
public class ProductReduceStockDTO {

    /** 商品编号 */
    private Long productId;
    /** 数量 */
    private Integer amount;

    // ... 省略 setter/getter 方法
}
