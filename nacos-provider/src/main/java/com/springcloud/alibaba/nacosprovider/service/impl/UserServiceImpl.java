package com.springcloud.alibaba.nacosprovider.service.impl;


import com.dubboapi.param.UserAddDTO;
import com.dubboapi.param.UserDTO;
import com.dubboapi.service.UserService;

@org.apache.dubbo.config.annotation.Service(protocol = "dubbo", version = "1.0.0")
public class UserServiceImpl implements UserService {

    public UserDTO get(Integer id) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(id);
        userDTO.setName("没有昵称：" + id);
        userDTO.setGender(id % 2 + 1); // 1 - 男；2 - 女
        return userDTO;
    }


    public Integer add(UserAddDTO addDTO) {
        return (int) (System.currentTimeMillis() / 1000); // 嘿嘿，随便返回一个 id
    }
}
