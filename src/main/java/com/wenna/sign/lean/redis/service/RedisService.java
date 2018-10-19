package com.wenna.sign.lean.redis.service;

import com.google.gson.*;
import com.wenna.sign.lean.redis.pojo.Student;
import org.apache.tomcat.jni.Time;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * 声明redis处理类
 */
@Repository
public class RedisService {


    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * redis 增加数据配置
     * @param key
     * @param obj
     * @param times
     */
    public void add(String key,Object obj,Long times){
        String objStr = this.getJsonObj(obj);
        stringRedisTemplate.opsForValue().set(key,objStr,times, TimeUnit.MINUTES);
    }


    /**
     * 根据关键字得到合同信息
     * @param key
     * @param classOfT
     * @return
     */
    public Object get(String key,Class classOfT){
        List requestlist = new ArrayList();
        String source = stringRedisTemplate.opsForValue().get(key);
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss Z").create();
        if (!(source.startsWith("["))) {
            return gson.fromJson(source, classOfT);
        }
        JsonParser parser = new JsonParser();
        JsonArray Jarray = parser.parse(source).getAsJsonArray();
        for (JsonElement obj : Jarray) {
            Object cse = gson.fromJson(obj, classOfT);
            requestlist.add(cse);
        }
        return requestlist;
    }

    /**
     * 操作string字符串
     * @param obj
     */
    public void operateStr(Student obj){
        //存入数据
        redisTemplate.opsForValue().set(obj.getAccount(),obj.toString());
        //获取数据
        System.out.println(">>>>"+redisTemplate.opsForValue().get(obj.getAccount()));
    }


    /**
     * redis 操作集合set数据
     * @param setName
     * @param set
     */
    public void operateSet(String setName ,Set set){
        //存入set
        redisTemplate.opsForSet().add(setName,set);
        //读取set
        redisTemplate.opsForZSet().add(setName,set);
        Set resultSet = redisTemplate.opsForSet().members(setName);
        System.out.println(">>>>"+resultSet);
    }


    /**
     * redis 操作map集合
     * @param mapName
     * @param map
     * hashmap
     *
     */
    public void operateMap(String mapName, Map<String,String> map){
        //存入map
        redisTemplate.opsForHash().putAll(mapName,map);


        map.put("key","value");
        //获取map

        //得到所有的hashmap
        Map<String,String> resultMap =redisTemplate.opsForHash().entries(mapName);
        System.out.println(">>>"+resultMap);
        //redis获取的到所有的value的值
        List<String> valueList = redisTemplate.opsForHash().values(mapName);
        System.out.println(">>>>"+valueList);
        //获取所有的key值
        Set<String> keySet = redisTemplate.opsForHash().keys(mapName);
        System.out.println(">>>"+keySet);

        //根据key和map的键得到对应的值
        String value = (String)redisTemplate.opsForHash().get(mapName,"key");

    }


    /**
     *
     * @param listName
     * @param type
     * @param list
     */
    public void operateList(String listName ,String type,List<String> list){

        if("left".equals(type)){
            //左侧存储
            redisTemplate.opsForList().leftPush(listName,list);
            //左侧读取
            List<String> leftList =  (List<String>) redisTemplate.opsForList().leftPop(listName);
            System.out.println(leftList);
        }else{
            //右侧存储

        }

    }


    /**
     * 将数据转成json格式
     * @param obj
     * @return
     */
    String getJsonObj(Object obj){
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss Z")
                .create();
        return gson.toJson(obj);
    }
}
