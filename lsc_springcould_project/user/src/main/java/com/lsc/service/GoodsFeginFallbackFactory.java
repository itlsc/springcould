package com.lsc.service;

import com.lsc.util.ResponseResult;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class GoodsFeginFallbackFactory implements FallbackFactory<GoodsFeignClient> {
    @Override
    public GoodsFeignClient create(Throwable throwable) {
        return new GoodsFeignClient() {
            @Override
            public Object getGoods() {
                System.out.printf(throwable.getMessage());
                return ResponseResult.error("服务器正在维护中  GoodsFeginFallbackFactory  请求失败");
            }
        };
    }
}
