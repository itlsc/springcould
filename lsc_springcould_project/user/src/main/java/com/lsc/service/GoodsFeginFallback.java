package com.lsc.service;

import com.lsc.util.ResponseResult;
import org.springframework.stereotype.Component;

@Component
public class GoodsFeginFallback implements GoodsFeignClient {
    @Override
    public Object getGoods() {
        return ResponseResult.error("服务器正在维护中GoodsFeginFallback  请求失败");
    }
}
