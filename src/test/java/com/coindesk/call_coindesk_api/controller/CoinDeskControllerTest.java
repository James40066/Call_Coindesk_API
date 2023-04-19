package com.coindesk.call_coindesk_api.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@SpringBootTest
@AutoConfigureMockMvc
class CoinDeskControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Test
    void coinDeskAPI() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/coindesk/CoinDeskAPI?api_url=https://api.coindesk.com/v1/bpi/currentprice.json");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.chartName", Matchers.equalTo("Bitcoin")));

    }

    @Test
    void newCoinDeskAPI() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/coindesk/NewCoinDeskAPI?api_url=https://api.coindesk.com/v1/bpi/currentprice.json");

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.upTime", Matchers.notNullValue()));

    }

    @Test
    void insert() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/coindesk/coin")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\":\"HKD\",\"symbol\":\"&#165;\",\"rate\":\"29,449.8807\",\"description\":\"港幣\",\"rate_float\":29449.8807}")
                ;

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(201));

    }

    @Test
    void update() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/coindesk/coin/{dbCoinDeskId}",5)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\":\"NTD\",\"symbol\":\"&#165;\",\"rate\":\"29,449.8807\",\"description\":\"新台幣\",\"rate_float\":29449.8807}")
                ;

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void delete() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/coindesk/coin/{dbCoinDeskId}",6);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    void get() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/coindesk/coin/{dbCoinDeskId}",1);

        mockMvc.perform(requestBuilder)
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.code", Matchers.equalTo("USD")));
    }
}