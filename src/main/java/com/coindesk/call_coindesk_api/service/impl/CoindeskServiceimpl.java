package com.coindesk.call_coindesk_api.service.impl;

import com.coindesk.call_coindesk_api.bean.Coin;
import com.coindesk.call_coindesk_api.bean.CoinDesk;
import com.coindesk.call_coindesk_api.bean.DbCoinDesk;
import com.coindesk.call_coindesk_api.bean.NewCoinDesk;
import com.coindesk.call_coindesk_api.dao.DbCoinDeskRepository;
import com.coindesk.call_coindesk_api.service.CoindeskService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    DbCoinDeskRepository dbCoinDeskRepository;
    private static final String API_URL = "https://api.coindesk.com/v1/bpi/currentprice.json";
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
    public String callApi() {
        String json = restTemplate.getForObject(API_URL, String.class);
        log.info("json=>" + json);
        return json;
    }

    @Override
    public CoinDesk getCoindesk() throws Exception {
        String json = callApi();
        CoinDesk coinDesk = objectMapper.readValue(json, CoinDesk.class);
        return coinDesk;
    }

    @Override
    public NewCoinDesk transCoindesk() throws Exception {
        String json = callApi();
        JSONObject jsonObject = new JSONObject(json);
        JSONObject bpi = jsonObject.getJSONObject("bpi");
        List<Coin> coins = new ArrayList<>();

        Iterator<String> keys = bpi.keys();
        while(keys.hasNext()) {
            String key = keys.next();
            if (bpi.get(key) instanceof JSONObject) {
                JSONObject jsob = bpi.getJSONObject(key);
                Coin coin = new Coin();
                coin.setCode(jsob.getString("code"));
                coin.setDescription(checkCoinDesc(jsob.getString("code")));
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

    @Override
    public boolean insert(DbCoinDesk dbCoinDesk){
        boolean is_save = true;
        try{
            dbCoinDeskRepository.save(dbCoinDesk);
        }catch (Exception ex){
            is_save = false;
        }
        return is_save;
    }

    @Override
    public boolean update(DbCoinDesk dbCoinDesk, Integer dbCoinDeskId) throws Exception {
        boolean is_save = true;
        try{
            DbCoinDesk d =  dbCoinDeskRepository.findById(dbCoinDeskId).orElse(null);
            d.setCode(dbCoinDesk.getCode());
            d.setSymbol(dbCoinDesk.getSymbol());
            d.setRate(dbCoinDesk.getRate());
            d.setDescription(dbCoinDesk.getDescription());
            d.setRate_float(dbCoinDesk.getRate_float());
            dbCoinDeskRepository.save(d);
        }catch (Exception ex){
            is_save = false;
        }
        return is_save;
    }

    @Override
    public boolean delete(Integer dbCoinDeskId) throws Exception {
        boolean is_delete = true;
        try{
            dbCoinDeskRepository.deleteById(dbCoinDeskId);
        }catch (Exception ex){
            is_delete = false;
        }
        return is_delete;
    }

    @Override
    public DbCoinDesk get(Integer dbCoinDeskId) throws Exception {
        DbCoinDesk dbCoinDesk =  dbCoinDeskRepository.findById(dbCoinDeskId).orElse(null);

        if(dbCoinDesk != null){
            return  dbCoinDesk;
        }else{
            return  null;
        }
    }

}
