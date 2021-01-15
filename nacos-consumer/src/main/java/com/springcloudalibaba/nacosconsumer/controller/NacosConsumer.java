package com.springcloudalibaba.nacosconsumer.controller;

import com.dubboapi.param.UserAddDTO;
import com.dubboapi.param.UserDTO;
import com.dubboapi.service.UserService;
import com.springcloudalibaba.nacosconsumer.fegin.UserFeignClient;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class NacosConsumer {

    @Autowired
    private DiscoveryClient discoveryClient;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;
    @Autowired
    private UserFeignClient userFeignClient;
    @Reference(version = "1.0.0")
    private UserService userService;


    @GetMapping("/hello")
    public String hello(String name) {
        // <1> 获得服务 `demo-provider` 的一个实例
        ServiceInstance instance;
        if (true) {
            // 获取服务 `demo-provider` 对应的实例列表
            List<ServiceInstance> instances = discoveryClient.getInstances("nacos-provider");
            // 选择第一个
            instance = instances.size() > 0 ? instances.get(0) : null;
        } else {
            instance = loadBalancerClient.choose("nacos-provider");
        }
        // <2> 发起调用
        if (instance == null) {
            throw new IllegalStateException("获取不到实例");
        }
        String targetUrl = instance.getUri() + "/nacosTest?name=" + name;
        String response = restTemplate.getForObject(targetUrl, String.class);
        // 返回结果
        return "consumer:" + response;
    }

    @GetMapping("/user/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        return userService.get(id);
    }

    @PostMapping("/user/add")
    public Integer add(UserAddDTO addDTO) {
        return userService.add(addDTO);
    }

    @GetMapping("/user/getId")
    public UserDTO getId(@RequestParam("id") Integer id) {
        return userFeignClient.get(id);
    }
}
