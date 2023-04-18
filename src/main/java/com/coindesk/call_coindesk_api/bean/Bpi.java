package com.coindesk.call_coindesk_api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Bpi {
    @JsonProperty("USD")
    Usd usd;

    @JsonProperty("GBP")
    Gbp gbp;

    @JsonProperty("EUR")
    Eur eur;

    public Usd getUsd() {
        return usd;
    }

    public void setUsd(Usd usd) {
        this.usd = usd;
    }

    public Gbp getGbp() {
        return gbp;
    }

    public void setGbp(Gbp gbp) {
        this.gbp = gbp;
    }

    public Eur getEur() {
        return eur;
    }

    public void setEur(Eur eur) {
        this.eur = eur;
    }
}
