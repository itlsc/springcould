package com.lsc.controller;

import com.lsc.util.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class goodsController {

     @RequestMapping("/getOrder")
      public ResponseResult getGoods(){
         return ResponseResult.success("getOrder2 success");
     }

}
