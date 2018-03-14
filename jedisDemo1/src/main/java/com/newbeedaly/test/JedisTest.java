package com.newbeedaly.test;

import redis.clients.jedis.Jedis;

public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("140.143.26.141",6379);
        jedis.auth("pass");
        jedis.set("name","newbeedaly.cn");
        String value = jedis.get("name");
        System.out.printf(value);
        jedis.close();
    }
}
