package com.springcloud.alibaba.nacosprovider.controller;

import com.springcloud.alibaba.nacosprovider.param.UserAddDTO;
import com.springcloud.alibaba.nacosprovider.param.UserDTO;
import com.springcloud.alibaba.nacosprovider.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class NacosProviderController {

    @Autowired
    private UserService userService;

    @RequestMapping("/nacosTest")
    public String nacosTest(@RequestParam("name") String name){
        return name;
    }

    @GetMapping("/user/get")
    public UserDTO get(@RequestParam("id") Integer id) {
        return userService.get(id);
    }

    @PostMapping("/user/add")
    public Integer add(UserAddDTO addDTO) {
        return userService.add(addDTO);
    }
}
