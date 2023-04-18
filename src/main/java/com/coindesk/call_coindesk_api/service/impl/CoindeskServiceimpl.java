package com.coindesk.call_coindesk_api.service.impl;

import com.coindesk.call_coindesk_api.bean.Coin;
import com.coindesk.call_coindesk_api.bean.CoinDesk;
import com.coindesk.call_coindesk_api.bean.NewCoinDesk;
import com.coindesk.call_coindesk_api.service.CoindeskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component
@Slf4j
public class CoindeskServiceimpl implements CoindeskService {
    private static final String DATE_FORMAT_PATTERN = "yyyy/MM/dd HH:mm:ss";
    private static final String CURRENCY_EUR = "EUR";
    private static final String CURRENCY_GBP = "GBP";
    private static final String CURRENCY_USD = "USD";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final DateTimeFormatter dateTimeFormatter;

    public CoindeskServiceimpl() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
        this.dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
        this.restTemplate.getMessageConverters().add(converter);
    }

    @Override
    public String callApi(String url) {
        String json = restTemplate.getForObject(url, String.class);
        return json;
    }

    @Override
    public CoinDesk getCoindesk(String url) throws Exception {
        String json = callApi(url);
        log.info("json=>" + json);
        CoinDesk coinDesk = objectMapper.readValue(json, CoinDesk.class);
        return coinDesk;
    }

    @Override
    public NewCoinDesk transCoindesk(String url) throws Exception {
        JSONObject jsonObject = new JSONObject(callApi(url));
        JSONObject bpi = jsonObject.getJSONObject("bpi");
        List<Coin> coins = new ArrayList<>();

        Iterator<String> keys = bpi.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            if (bpi.get(key) instanceof JSONObject) {
                JSONObject jsob = bpi.getJSONObject(key);
                Coin coin = new Coin();
                coin.setCode(jsob.getString("code"));
                coin.setCodeDesc(checkCoinDesc(jsob.getString("code")));
                coin.setRate(jsob.getString("rate"));
                coins.add(coin);
            }
        }
        NewCoinDesk newCoinDesk = new NewCoinDesk();
        newCoinDesk.setCoins(coins);
        newCoinDesk.setUpTime(getNow());
        return newCoinDesk;
    }

    @Override
    public String getNow() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        return dateTimeFormatter.format(now);
    }

    @Override
    public String checkCoinDesc(String code) throws Exception {
        String codeDesc = "";
        switch (code) {
            case CURRENCY_EUR:
                codeDesc = "歐元";
                break;
            case CURRENCY_GBP:
                codeDesc = "英鎊";
                break;
            case CURRENCY_USD:
                codeDesc = "美元";
                break;
            default:
                codeDesc = "";
        }
        return codeDesc;
    }

}
