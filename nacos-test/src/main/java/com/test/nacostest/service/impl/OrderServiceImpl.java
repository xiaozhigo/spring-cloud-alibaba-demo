package com.test.nacostest.service.impl;

import com.test.nacostest.dao.OrderDao;
import com.test.nacostest.fegin.AccountServiceFeignClient;
import com.test.nacostest.fegin.ProductServiceFeignClient;
import com.test.nacostest.param.AccountReduceBalanceDTO;
import com.test.nacostest.param.OrderDO;
import com.test.nacostest.param.ProductReduceStockDTO;
import com.test.nacostest.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import io.seata.tm.api.transaction.Propagation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private AccountServiceFeignClient accountService;
    @Autowired
    private ProductServiceFeignClient productService;

    @GlobalTransactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class) // <1>
    public Integer createOrder(Long userId, Long productId, Integer price) {
        Integer amount = 1; // 购买数量，暂时设置为 1。

        logger.info("[createOrder] 当前 XID: {}", RootContext.getXID());

        ProductReduceStockDTO reduceStockDTO = new ProductReduceStockDTO();
        reduceStockDTO.setProductId(productId);
        reduceStockDTO.setAmount(amount);
        // <2> 扣减库存
        productService.reduceStock(reduceStockDTO);

        // <3> 扣减余额
        AccountReduceBalanceDTO accountReduceBalanceDTO = new AccountReduceBalanceDTO();
        accountReduceBalanceDTO.setUserId(userId);
        accountReduceBalanceDTO.setPrice(price);
        accountService.reduceBalance(accountReduceBalanceDTO);

        // <4> 保存订单
        OrderDO order = new OrderDO();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setPayAmount(amount * price);
        orderDao.saveOrder(order);
        logger.info("[createOrder] 保存订单: {}", order.getId());

        // 返回订单编号
        return order.getId();
    }

}
