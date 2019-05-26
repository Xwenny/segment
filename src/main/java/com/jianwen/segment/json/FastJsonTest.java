package com.jianwen.segment.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.junit.Test;

import java.util.*;

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

    /**
     * json转对象
     */
    @Test
    public void jsonToUser(){
        String userJson = "{\"age\":30,\"name\":\"Skye\"}";
        User user = JSON.parseObject(userJson,User.class);
        System.out.println(user);

    }

    /**
     * 格式化例二的list
     */
    @Test
    public void listToJson2(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("key1", "One");
        map1.put("key2", "Two");

        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("key3", "Three");
        map2.put("key2", "Four");

        list.add(map1);
        list.add(map2);
        String listJson = JSON.toJSONString(list,true);
        System.out.println(listJson);
    }

    @Test
    public void listToJson3(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("key1", "One");
        map1.put("key2", "Two");

        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("key3", "Three");
        map2.put("key2", "Four");

        list.add(map1);
        list.add(map2);
        String listJson = JSON.toJSONString(list,SerializerFeature.PrettyFormat);
        System.out.println(listJson);
    }


    /**
     * 缺省情况下会将date转成long
     */
    @Test
    public void dataFormat(){
        String dateJson = JSON.toJSONString(new Date());
        System.out.println(dateJson);
    }

    /**
     * 使用SerializerFeature特性格式化日期
     */
    @Test
    public void dataFormat2(){
        String dateJson = JSON.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat);
        System.out.println(dateJson);
    }

    /**
     * 指定输出日期格式
     */
    @Test
    public void datePrintFormat(){
        String dateJson = JSON.toJSONStringWithDateFormat(new Date(),"yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(dateJson);
    }

    /**
     * 使用单引号
     */
    @Test
    public void singleQuotes(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("key1", "One");
        map1.put("key2", "Two");

        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("key3", "Three");
        map2.put("key2", "Four");

        list.add(map1);
        list.add(map2);
        String listJson = JSON.toJSONString(list,SerializerFeature.UseSingleQuotes);
        System.out.println(listJson);
    }

    /**
     * 输出null字段
     */
    @Test
    public void printNull(){
        Map<String,Object> map = new HashMap<String, Object>();
        String b = null;
        Integer i = 1;
        map.put("a",b);
        map.put("b",i);
        String listJson = JSON.toJSONString(map,SerializerFeature.WriteMapNullValue);
        System.out.println(listJson);
    }

    /**
     * 序列化是写入类型信息
     */
    @Test
    public void writeIn(){
        User user = new User();
        user.setAge(18);
        user.setName("liyabin");
        String listJson = JSON.toJSONString(user,SerializerFeature.WriteClassName);
        System.out.println(listJson);
    }

    /**
     * 将上个例子反序列化
     */
    @Test
    public void autoSerialize(){
        User user = new User();
        user.setAge(18);
        user.setName("liyabin");
        String listJson = JSON.toJSONString(user,SerializerFeature.WriteClassName);
        System.out.println(listJson);
        User user1 = (User)JSON.parse(listJson);
        System.out.println(user1.getAge());
    }

    /**
     * 集合反序列化
     */
    @Test
    public void listAutoSerialize(){
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("key1", "One");
        map1.put("key2", "Two");

        Map<String,Object> map2 = new HashMap<String,Object>();
        map2.put("key1", "Three");
        map2.put("key2", "Four");

        list.add(map1);
        list.add(map2);
        String listJson = JSON.toJSONString(list);
        List<Map> list1 = JSON.parseArray(listJson,Map.class);
        for (Map<String,Object> map : list1){
            System.out.println(map.get("key1"));
            System.out.println(map.get("key2"));
        }
    }

    /**
     * 泛型的反序列化
     */
    @Test
    public void autoSerialize2(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "One");
        map.put("key2", "Two");
        String mapJson = JSON.toJSONString(map);
        Map<String,Object> map1 = JSON.parseObject(mapJson,new TypeReference<Map<String,Object>>(){});
        System.out.println(map1.get("key1"));
        System.out.println(map1.get("key2"));
    }

    /**
     * 将map转成jsonobject，然后添加元素，输出
     */
    @Test
    public void mapToJsonObject(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "One");
        map.put("key2", "Two");
        JSONObject jsonObject = new JSONObject(map);
        jsonObject.put("key3","Three");
        System.out.println(jsonObject.get("key1"));
        System.out.println(jsonObject.get("key2"));
        System.out.println(jsonObject.get("key3"));

    }

    /**
     * 将list对象转成JSONArray，然后输出
     */
    @Test
    public void listToJsonArray(){
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("key1", "One");
        map.put("key2", "Two");
        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("key1", "Three");
        map2.put("key2", "Four");
        list.add(map);
        list.add(map2);
        JSONArray jsonArray = JSONArray.parseArray(JSON.toJSONString(list));
        for (int i=0;i<jsonArray.size();i++){
            System.out.println(jsonArray.get(i));
        }
    }

    @Test
    public void temToJson(){
        User user = new User();
        user.setName("Skye");
        user.setAge(23);
        String userJson = JSON.toJSONString(user);
        System.out.println(userJson);

    }

}
