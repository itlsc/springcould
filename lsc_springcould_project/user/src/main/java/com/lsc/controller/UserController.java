package com.lsc.controller;

import com.lsc.service.GoodsFeignClient;
import com.lsc.util.ResponseResult;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {


      //注入Feigin接口
      @Autowired
    GoodsFeignClient goodsFeignClient;
    //注入RestTemplate
     @Autowired
     private RestTemplate restTemplate;

   @RequestMapping("/getUser")
    public ResponseResult getUser(){
       return     ResponseResult.success("操作成功", goodsFeignClient.getGoods());
    }
    @RequestMapping("/getOrder")
    public ResponseResult getOrder(){
        return     ResponseResult.success("操作成功",
                restTemplate.getForObject("http://provide-order/getOrder",Object.class));
    }

    public ResponseResult fallbackMethod(){
       return ResponseResult.error("服务器正在维护中 请求失败");
    }
}
