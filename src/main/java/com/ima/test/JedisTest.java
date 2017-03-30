package com.ima.test;

//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;

/**
 * Created by ${符柱成} on 2017/3/29.
 */
public class JedisTest {
    //单机版
//    @Test
//    public void testJedisSingle() throws Exception{
//        //创建一个jedis对象
//        Jedis jedis = new Jedis("127.0.0.1",6379);
//        jedis.set("test", "hello jedis");
//        String string = jedis.get("test");
//        System.out.println(string);
//        jedis.close();
//    }
//    //使用连接池
//    @Test
//    public void testJedisPool() throws Exception{
//        //创建一个链接池对象
//        //系统中应该是单例的
//        JedisPool jedisPool = new JedisPool("127.0.0.1",6379);
//        //从连接池中获得一个连接
//        Jedis jedis = jedisPool.getResource();
//        String result = jedis.get("test");
//        System.out.println(result);
//        //jedis必须关闭
//        jedis.close();
//        //系统关闭时关闭连接池
//        jedisPool.close();
//    }
}
