package com.coindesk.call_coindesk_api.service.impl;

import com.coindesk.call_coindesk_api.bean.Coin;
import com.coindesk.call_coindesk_api.bean.CoinDesk;
import com.coindesk.call_coindesk_api.bean.DbCoinDesk;
import com.coindesk.call_coindesk_api.bean.NewCoinDesk;
import com.coindesk.call_coindesk_api.service.CoindeskService;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class CoindeskServiceimplTest {
    @Autowired
    CoindeskService coindeskService;

    @Test
    void callApi() throws Exception {
        JSONObject jsob = new JSONObject(coindeskService.callApi());

        assertNotNull(jsob);
        assertEquals("Bitcoin",jsob.getString("chartName"));
    }

    @Test
    void getCoindesk() throws Exception {
        CoinDesk coinDesk = coindeskService.getCoindesk();

        assertNotNull(coinDesk);
        assertEquals("Bitcoin",coinDesk.getChartName());
    }

    @Test
    void transCoindesk() throws Exception {
        NewCoinDesk newCoinDesk = coindeskService.transCoindesk();
        List<Coin> coin = newCoinDesk.getCoins();

        assertNotNull(newCoinDesk);
        assertFalse(coin.isEmpty());
    }

    @Test
    void getNow() throws Exception {
        String now = coindeskService.getNow();
        assertNotNull(now);
        assertTrue(now.length() > 0);
    }

    @Test
    void checkCoinDesc() throws Exception {
        String desc = coindeskService.checkCoinDesc("EUR");
        assertNotNull(desc);
        assertEquals("歐元",desc);
    }

    @Test
    void insert() throws Exception {
        DbCoinDesk dbCoinDesk = new DbCoinDesk();
        dbCoinDesk.setCode("CNY");
        dbCoinDesk.setSymbol("&#165");
        dbCoinDesk.setRate("30,231.4855");
        dbCoinDesk.setDescription("人民幣");
        dbCoinDesk.setRate_float(29449.8807);

        boolean status = coindeskService.insert(dbCoinDesk);

        assertTrue(status);
    }

    @Test
    void update() throws Exception {
        DbCoinDesk dbCoinDesk = new DbCoinDesk();
        dbCoinDesk.setCode("NTD");
        dbCoinDesk.setSymbol("&#165");
        dbCoinDesk.setRate("30,231.4855");
        dbCoinDesk.setDescription("新台幣");
        dbCoinDesk.setRate_float(29449.8807);

        boolean status = coindeskService.update(dbCoinDesk,5);

        assertTrue(status);
    }

    @Test
    void delete() throws Exception {
        boolean status = coindeskService.delete(5);

        assertTrue(status);
    }

    @Test
    void get() throws Exception {
        DbCoinDesk dbCoinDesk = coindeskService.get(1);

        assertNotNull(dbCoinDesk);
        assertEquals("USD",dbCoinDesk.getCode());
    }
}