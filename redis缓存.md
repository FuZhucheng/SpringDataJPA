### 本工程加入了redis缓存
#### （1）.maven：
```
   <!-- Redis -->
    <dependency>
      <groupId>redis.clients</groupId>
      <artifactId>jedis</artifactId>
      <version>2.7.3</version>
    </dependency>

```
#### （2）.一个redis配置，交给spring管理
```
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!-- 本jedis的jar为2.7.0版本 -->


    <!-- jedisPool配置信息 -->
    <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
        <property name="maxTotal" value="1000"/> <!-- 控制一个pool可分配多少个jedis实例 -->
        <property name="maxIdle" value="200" />   <!-- 控制一个pool最多有多少个状态为idle(空闲)的jedis实例 -->
        <property name="maxWaitMillis" value="2000" />  <!-- 表示当borrow一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException -->
        <property name="testOnBorrow" value="true" /> <!-- 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的 -->
    </bean>

    <!-- jedis客户端单机版配置 -->
    <bean id="jedisPool" class="redis.clients.jedis.JedisPool" scope="singleton">
        <constructor-arg name="poolConfig" ref="jedisPoolConfig" />
        <constructor-arg name="host" value="127.0.0.1"></constructor-arg>
        <constructor-arg name="port" value="6379"></constructor-arg>
    </bean>



    <bean id="jedisClient" class="com.ima.service.JedisClientSingle" />

</beans>
```
```
//编写完配置文件后，记得在web.xml注册给spring
  <!-- Spring 配置 -->
  <context-param>
    <param-name>contextConfigLocation</param-name>
    <param-value>classpath:META-INF/spring/spring-jpa.xml,classpath:META-INF/spring/spring-jedis.xml</param-value>
  </context-param>
```
#### （3）测试接口：
```
public interface JedisClient {

    public String get(String key);
    public String set(String key, String value);
    public String hget(String hkey, String key);
    public long hset(String hkey, String key, String value);
    public long incr(String key);
    public long expire(String key, int second);
    public long ttl(String key);
    public long del(String key);
    public long hdel(String hkey, String key);

}
```
#### （4）接口实现类：
```
@Service
public class JedisClientSingle implements JedisClient {


    @Bean
    public JedisPool getJedisPool(){
        return new JedisPool("127.0.0.1",6379);
    }
    @Override
    public String get(String key) {
        Jedis jedis = getJedisPool().getResource();
        String string = jedis.get(key);
        jedis.close();
        return string;
    }

    @Override
    public String set(String key, String value) {
        Jedis jedis = getJedisPool().getResource();
        String string = jedis.set(key, value);
        jedis.close();
        return string;
    }

    @Override
    public String hget(String hkey, String key) {
        System.out.println("jedisPool   "+getJedisPool());
        Jedis jedis = getJedisPool().getResource();
        System.out.println("jedis   "+jedis);
        String string = jedis.hget(hkey, key);
        jedis.close();
        return string;
    }

    @Override
    public long hset(String hkey, String key, String value) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.hset(hkey, key, value);
        jedis.close();
        return result;
    }

    @Override
    public long incr(String key) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.incr(key);
        jedis.close();
        return result;
    }

    @Override
    public long expire(String key, int second) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.expire(key, second);
        jedis.close();
        return result;
    }

    @Override
    public long ttl(String key) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.ttl(key);
        jedis.close();
        return result;
    }

    @Override
    public long del(String key) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.del(key);
        jedis.close();
        return result;
    }

    @Override
    public long hdel(String hkey, String key) {
        Jedis jedis = getJedisPool().getResource();
        Long result = jedis.hdel(hkey,key);
        jedis.close();
        return result;
    }
}
```
#### （5）编写测试操作：
```
  //查历史记录
    @RequestMapping(value = "/findHistory", method = {RequestMethod.GET, RequestMethod.POST})
    public String findHistory(Long id) {
        DTO dto = new DTO();
        List<IDouChange> iDouChangeList = null;
        System.out.println("jedisClient  :" + jedisClient);
        try {
            String resulthget = jedisClient.hget("个人积分记录", id + "");
            if (resulthget != null) {
                //字符串转为list
                System.out.println("有缓存啦啦啦！！！");
                JSONArray array = JSONArray.parseArray(resulthget);
                iDouChangeList = (List) array;
            } else {
                System.out.println("个人积分记录没查过");
                iDouChangeList = aiDouService.getHistory(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (iDouChangeList == null) {
            dto.code = "-1";
            dto.msg = "Have not updateAvatar";
        }
        try {
            String cacheString = JsonUtils.objectToJson(iDouChangeList);
            jedisClient.hset("个人积分记录", id + "", cacheString);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return JSON.toJSONString(iDouChangeList);
    }
```