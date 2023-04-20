package com.coindesk.call_coindesk_api.bean;

import javax.persistence.*;

@Entity
@Table(name = "coin")
public class DbCoinDesk {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;

    @Column(name = "code")
    String code;

    @Column(name = "symbol")
    String symbol;

    @Column(name = "rate")
    String rate;

    @Column(name = "description")
    String description;

    @Column(name = "rate_float")
    Double rate_float;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Double getRate_float() {
        return rate_float;
    }

    public void setRate_float(Double rate_float) {
        this.rate_float = rate_float;
    }
}
