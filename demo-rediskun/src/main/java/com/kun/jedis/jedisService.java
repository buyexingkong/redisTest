package com.kun.jedis;


import org.junit.jupiter.api.Test;
import redis.clients.jedis.GeoCoordinate;
import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class jedisService {
    public static void main(String[] args) {
        Jedis jedis =  new Jedis("192.168.101.52", 6379);
        String pong = jedis.ping();
        System.out.println("连接成功："+pong);
        jedis.close();
    }

    @Test
    public void demo1(){
        Jedis jedis =  new Jedis("192.168.101.52", 6379);
        jedis.set("k1","v1");
        jedis.lpush("l1","lv1","lv2");
        jedis.sadd("s1","id","1","name","zhangsan");

        Map<String,String> userMap = new HashMap<>();
        userMap.put("id","1");
        userMap.put("name","zhangsan");
        jedis.hset("h1",userMap);

        Map<String,Double> zSetValueMap = new HashMap<>();
        zSetValueMap.put("n1",100D);
        zSetValueMap.put("n2",200D);
        jedis.zadd("z1", zSetValueMap);

        jedis.setbit("b1",1L,true);

        Map<String, GeoCoordinate> geoMap = new HashMap<>();

        GeoCoordinate geoTemp = new GeoCoordinate(100L,80L);
        geoMap.put("geo1",geoTemp);
        jedis.pfadd("pf1","java","c++","java");
        jedis.geoadd("geo1",geoMap);

        Set<String> keys = jedis.keys("*");
        keys.forEach(System.out::println);
        jedis.close();
    }
}
