package com.coindesk.call_coindesk_api.bean;

import java.util.List;
public class NewCoinDesk {
    List<Coin> coins;

    String upTime;

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> coins) {
        this.coins = coins;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

}
