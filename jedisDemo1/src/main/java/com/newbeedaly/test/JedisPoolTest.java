package com.newbeedaly.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class JedisPoolTest {
    public static void main(String[] args) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(100);// 设置最大连接数
        config.setMaxIdle(10);// 最大空闲连接数

        JedisPool jedisPool = new JedisPool(config,"140.143.26.141",6379);
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.auth("pass");
            jedis.set("name","newbeedaly.cn");
            String value = jedis.get("name");
            System.out.printf(value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(jedis!=null){
                jedis.close();
            }
            if(jedisPool!=null){
                jedisPool.close();
            }
        }

    }
}
