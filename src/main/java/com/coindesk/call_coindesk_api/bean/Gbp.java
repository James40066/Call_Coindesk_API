package com.coindesk.call_coindesk_api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Gbp {
    @JsonProperty("code")
    String code;

    @JsonProperty("symbol")
    String symbol;

    @JsonProperty("rate")
    String rate;

    @JsonProperty("description")
    String description;

    @JsonProperty("rate_float")
    double rate_float;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRate_float() {
        return rate_float;
    }

    public void setRate_float(double rate_float) {
        this.rate_float = rate_float;
    }

}
