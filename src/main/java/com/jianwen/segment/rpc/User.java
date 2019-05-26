package com.jianwen.segment.rpc;

import java.io.Serializable;

/**
 * Created by lybuestc on 17/6/6.
 */
public class User implements Serializable{
    String userName;
    int age;

    public User(String userName, int age) {
        this.userName = userName;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
