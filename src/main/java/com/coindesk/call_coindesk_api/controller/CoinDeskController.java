package com.coindesk.call_coindesk_api.controller;

import com.coindesk.call_coindesk_api.bean.Bpi;
import com.coindesk.call_coindesk_api.bean.CoinDesk;
import com.coindesk.call_coindesk_api.bean.NewCoinDesk;
import com.coindesk.call_coindesk_api.service.CoindeskService;
import com.power.common.model.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@RestController
@RequestMapping("/coindesk")
@Slf4j
public class CoinDeskController {
    @Autowired
    CoindeskService coindeskService;

    @GetMapping("/call_CoinDesk")
    public CoinDesk call_CoinDesk(@RequestParam String api_url) throws Exception{

        CoinDesk coinDesk = coindeskService.getCoindesk(api_url);

        //http://localhost:8081/coindesk/call_CoinDesk?api_url=https://api.coindesk.com/v1/bpi/currentprice.json
        return coinDesk;
    }

    @GetMapping("/call_NewCoinDesk")
    public NewCoinDesk call_NewCoinDesk(@RequestParam String api_url) throws Exception{

        NewCoinDesk newCoinDesk = coindeskService.transCoindesk(api_url);

        //http://localhost:8081/coindesk/call_NewCoinDesk?api_url=https://api.coindesk.com/v1/bpi/currentprice.json
        return newCoinDesk;
    }





}
