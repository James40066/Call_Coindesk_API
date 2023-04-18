package com.coindesk.call_coindesk_api.controller;

import com.power.common.model.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class test {

    @RequestMapping("/test")
    public CommonResult test(){
        System.out.println("CommonResult_test成功");
        return CommonResult.ok();
    }




}
