package com.jianwen.segment.json;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: jianwen
 * @Date: 2018/6/3 下午9:21
 */
public class FastJsonTest {

    @Test
    public void mapToJson(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "One");
        map.put("key2", "Two");
        String mapJson = JSON.toJSONString(map);
        System.out.println(mapJson);
    }

    @Test
    public void listToJson(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("key1", "One");
        map1.put("key2", "Two");

        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("key3", "Three");
        map2.put("key2", "Four");

        list.add(map1);
        list.add(map2);

        String listJson = JSON.toJSONString(list);
        System.out.println(listJson);
    }

    /**
     * 自定义JavaBean User转成JSON
     */
    @Test
    public void userToJson(){
        User user = new User();
        user.setName("Skye");
        user.setAge(23);
        String userJson = JSON.toJSONString(user);
        System.out.println(userJson);

    }

    @Test
    public void jsonToUser(){
        String userJson = "{\"age\":30,\"name\":\"Skye\"}";
        User user = JSON.parseObject(userJson,User.class);
        System.out.println(user);

    }
}
