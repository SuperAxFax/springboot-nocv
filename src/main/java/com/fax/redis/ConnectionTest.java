package com.fax.redis;

import redis.clients.jedis.Jedis;

public class ConnectionTest {
    public static void main(String[] args) {
        //连接本地的redis服务
        Jedis jedis = new Jedis("localhost");
        //如果redis设置了密码就执行：jedis.auth("123456");否则就不需要
        //查看服务是否运行
        System.out.println("服务正在运行"+jedis.ping());
    }
}
