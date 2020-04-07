package com.lsc.controller;

import com.lsc.util.ResponseResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class goodsController {

     @RequestMapping("/getGoods")
      public ResponseResult getGoods(){
        /* System.out.print(1/0);*/
/*         try {
             Thread.sleep(5*1000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }*/
         return ResponseResult.success("getGoods success");
     }

}
