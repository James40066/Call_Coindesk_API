package com.coindesk.call_coindesk_api.service;

import com.coindesk.call_coindesk_api.bean.CoinDesk;
import com.coindesk.call_coindesk_api.bean.NewCoinDesk;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.client.RestTemplate;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public interface CoindeskService {
    //取得JSON
    String callApi(String url) throws Exception;

    //呼叫Coindesk_API
    CoinDesk getCoindesk(String url) throws Exception;

    //呼叫Coindesk資料轉換_API
    NewCoinDesk transCoindesk(String url) throws Exception;

    //取得目前時間
    String getNow() throws Exception;

    String checkCoinDesc(String coin_desc)throws Exception;

}
