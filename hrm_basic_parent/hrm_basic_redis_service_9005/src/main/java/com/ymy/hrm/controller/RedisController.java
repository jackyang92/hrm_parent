package com.ymy.hrm.controller;

import com.ymy.hrm.util.RedisUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("/cache")
public class RedisController{
    @PostMapping
    public void set(@RequestParam("key")String key, @RequestParam("value")String value) {
        if (value.equals("[]"))
            RedisUtils.INSTANCE.getSource().setex(key,5*60, value);
        else
            RedisUtils.INSTANCE.set(key,value);
    }
    @PostMapping("/timeout")
    void set(@RequestParam("key")String key, @RequestParam("value")String value,@RequestParam("timeout")int timeout){

        Jedis jedis = RedisUtils.INSTANCE.getSource();
        jedis.setex(key, timeout, value);
        jedis.close();
    }
    @GetMapping
    public String get(@RequestParam("key")String key) {
        return RedisUtils.INSTANCE.get(key);
    }
}