package com.study.boot.redis.controller;

import com.study.boot.redis.util.RedisUtils;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sakura
 * @Create 2021-05-07 15:30
 */
@RestController
public class GoodController {

    private static final String REDIS_LOCK_KEY = "lockOneby";

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Value("${server.port}")
    private String serverPort;

    private final Lock lock = new ReentrantLock();

    @Autowired
    private Redisson redisson;

    @GetMapping("/buy_goods")
    public String buy_Goods() throws Exception {
        // 3.0版加分布式锁
        // 当前请求的 UUID + 线程名
        String value = UUID.randomUUID().toString()+Thread.currentThread().getName();

        // 9.0版上RedLock之 Redisson 落地实现
        // 获取锁
        RLock redissonLock = redisson.getLock(REDIS_LOCK_KEY);
        // 上锁
        redissonLock.lock();

        // 4.0版加finally释放锁，防止异常情况释放不了锁
        try {

            // 3.0版加分布式锁 setIfAbsent() 就相当于 setnx，如果不存在就新建锁
            //Boolean lockFlag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_KEY, value);
            // 5.0版加过期时间，防止宕机了未解锁
            // 设置过期时间为 10s
            //stringRedisTemplate.expire(REDIS_LOCK_KEY, 10L, TimeUnit.SECONDS);

            // 6.0 版本的代码：保证加锁和设置过期时间为原子操作
            // setIfAbsent() 就相当于 setnx，如果不存在就新建锁，同时加上过期时间保证原子性
            //Boolean lockFlag = stringRedisTemplate.opsForValue().setIfAbsent(REDIS_LOCK_KEY, value, 10L, TimeUnit.SECONDS);

            // 抢锁失败
//            if(!lockFlag){
//                return "抢锁失败 o(╥﹏╥)o";
//            }

            // 2.0 版本的代码：使用 synchronized 锁保证单机版程序在并发下的安全性
            //synchronized (this) {
            // 从 redis 中获取商品的剩余数量
            String result = stringRedisTemplate.opsForValue().get("goods:001");// get key =====看看库存的数量够不够
            int goodsNumber = result == null ? 0 : Integer.parseInt(result);
            String retStr = null;

            // 商品数量大于零才能出售
            if (goodsNumber > 0) {
                int realNumber = goodsNumber - 1;
                stringRedisTemplate.opsForValue().set("goods:001", String.valueOf(realNumber));
                retStr = "你已经成功秒杀商品，此时还剩余：" + realNumber + "件" + "\t 服务器端口: " + serverPort;
            } else {
                retStr = "商品已经售罄/活动结束/调用超时，欢迎下次光临" + "\t 服务器端口: " + serverPort;
            }
            System.out.println(retStr);
            // 3.0版释放分布式锁
            //stringRedisTemplate.delete(REDIS_LOCK_KEY);
            return retStr;
        //}
            // 4.0版加finally释放锁，防止异常情况释放不了锁
        } finally {
            // 7.0版 加判断是否是自己加的锁
//            if(value.equalsIgnoreCase(stringRedisTemplate.opsForValue().get(REDIS_LOCK_KEY))) {
//                stringRedisTemplate.delete(REDIS_LOCK_KEY); // 释放分布式锁
//            }
            // 8.1 开启事务不断监视 REDIS_LOCK_KEY 这把锁有没有被别人动过，如果已经被别人动过了，那么继续重新执行删除操作，否则就解除监视
//            while (true) {
//                //加事务，乐观锁
//                stringRedisTemplate.watch(REDIS_LOCK_KEY);
//                // 判断是否是自己加的锁
//                if (value.equalsIgnoreCase(stringRedisTemplate.opsForValue().get(REDIS_LOCK_KEY))) {
//                    // 开启事务
//                    stringRedisTemplate.setEnableTransactionSupport(true);
//                    stringRedisTemplate.multi();
//                    stringRedisTemplate.delete(REDIS_LOCK_KEY);
//                    // 判断事务是否执行成功，如果等于 null，就是没有删掉，删除失败，再回去 while 循环那再重新执行删除
//                    List<Object> list = stringRedisTemplate.exec();
//                    if (list == null) {
//                        continue;
//                    }
//                }
//                //如果删除成功，释放监控器，并且 break 跳出当前循环
//                stringRedisTemplate.unwatch();
//                break;
//            }


            // 8.2版本
            // 获取连接对象
//            Jedis jedis = RedisUtils.getJedis();
//            // lua 脚本，摘自官网
//            String script = "if redis.call('get', KEYS[1]) == ARGV[1]" + "then "
//                    + "return redis.call('del', KEYS[1])" + "else " + "  return 0 " + "end";
//            try {
//                // 执行 lua 脚本
//                Object result = jedis.eval(script, Collections.singletonList(REDIS_LOCK_KEY), Collections.singletonList(value));
//                // 获取 lua 脚本的执行结果
//                if ("1".equals(result.toString())) {
//                    System.out.println("------del REDIS_LOCK_KEY success");
//                } else {
//                    System.out.println("------del REDIS_LOCK_KEY error");
//                }
//            } finally {
//                // 关闭链接
//                if (null != jedis) {
//                    jedis.close();
//                }
//            }

            // 9.0版上RedLock之 Redisson 落地实现
            // 解锁
//            redissonLock.unlock();

            // 9.1版完善 在释放锁之前加一个判断：还在持有锁的状态，并且是当前线程持有的锁再解锁
            if (redissonLock.isLocked() && redissonLock.isHeldByCurrentThread()){
                redissonLock.unlock();
            }
        }
    }
}
