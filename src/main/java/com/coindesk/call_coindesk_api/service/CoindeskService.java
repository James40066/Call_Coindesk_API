package com.coindesk.call_coindesk_api.service;

import com.coindesk.call_coindesk_api.bean.CoinDesk;
import com.coindesk.call_coindesk_api.bean.NewCoinDesk;

import java.util.Date;

public interface CoindeskService {
    //取得JSON
    String call_Api(String url) throws Exception;

    //呼叫Coindesk_API
    CoinDesk get_Coindesk(String url) throws Exception;

    //呼叫Coindesk資料轉換_API
    NewCoinDesk trans_Coindesk(String url) throws Exception;

    //取得目前時間
    String get_now() throws Exception;

    String check_coin_desc(String coin_desc)throws Exception;

}
