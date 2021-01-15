package com.dubboapi.param;


import java.io.Serializable;


public class UserAddDTO implements Serializable {

    /**
     * 昵称
     */
    private String name;
    /**
     * 性别
     */
    private Integer gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }
}
