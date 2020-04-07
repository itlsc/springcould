package com.lsc.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name= "provide-goods",fallbackFactory = GoodsFeginFallbackFactory.class)
public interface GoodsFeignClient {
    @RequestMapping("/getGoods")
    public Object getGoods();
}
