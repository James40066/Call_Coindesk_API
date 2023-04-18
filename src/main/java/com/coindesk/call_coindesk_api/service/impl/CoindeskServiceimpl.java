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

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
@Slf4j
public class CoindeskServiceimpl implements CoindeskService {

    @Override
    public String call_Api(String url) {
        RestTemplate restTemplate = new RestTemplate();

        //解決restTemplate.getForObject不支援application/javascript
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.ALL));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        //CoinDesk coinDesk = restTemplate.getForObject(url, CoinDesk.class);

        String json = restTemplate.getForObject(url, String.class);

        return json;
    }

    @Override
    public CoinDesk get_Coindesk(String url) throws Exception {
        String json = call_Api(url);
        log.info("json=>" + json);

        ObjectMapper objectMapper = new ObjectMapper();

        CoinDesk coinDesk = objectMapper.readValue(json, CoinDesk.class);

        return coinDesk;
    }

    @Override
    public NewCoinDesk trans_Coindesk(String url) throws Exception {
        JSONObject jsonObject = new JSONObject(call_Api(url));
        JSONObject bpi = jsonObject.getJSONObject("bpi");

        List<Coin> coins = new ArrayList<>();

        Iterator<String> keys = bpi.keys();
        while(keys.hasNext()) {
            String key = keys.next();

            if (bpi.get(key) instanceof JSONObject) {
                JSONObject jsob = bpi.getJSONObject(key);
                Coin coin = new Coin();
                coin.setCode(jsob.getString("code"));
                coin.setCode_desc(check_coin_desc(jsob.getString("code")));
                coin.setRate(jsob.getString("rate"));
                coins.add(coin);
            }
        }
        NewCoinDesk newCoinDesk = new NewCoinDesk();
        newCoinDesk.setCoins(coins);
        newCoinDesk.setUpTime(get_now());

        return newCoinDesk;
    }

    @Override
    public String get_now() throws Exception {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String date = dateFormat.format(new Date());

        return date;
    }

    @Override
    public String check_coin_desc(String coin_desc) throws Exception {
        String str = "";

        if(coin_desc.equals("EUR")){
            str = "歐元";
        }else if(coin_desc.equals("GBP")){
            str = "英鎊";
        }else if(coin_desc.equals("USD")){
            str = "美元";
        }

        return str;
    }


}
