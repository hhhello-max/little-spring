package com.framework.test.annotationTest;

import com.framework.stereotype.Component;

@Component("userService")
public class UserService1 implements IUserService{

    private String nation;

    @Override
    public String queryUser() {
        return "我的国家是"+nation;
    }

    @Override
    public String register(String name) {
        return name+"注册成功";
    }

    @Override
    public String toString() {
        return "UserService{" +
                "nation='" + nation + '\'' +
                '}';
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }
}
