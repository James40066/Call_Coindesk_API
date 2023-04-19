package com.coindesk.call_coindesk_api.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Time {
    @JsonProperty("updated")
    private String updated;
    @JsonProperty("updatedISO")
    private String updatedISO;
    @JsonProperty("updateduk")
    private String updateduk;


    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getUpdatedISO() {
        return updatedISO;
    }

    public void setUpdatedISO(String updatedISO) {
        this.updatedISO = updatedISO;
    }

    public String getUpdateduk() {
        return updateduk;
    }

    public void setUpdateduk(String updateduk) {
        this.updateduk = updateduk;
    }
}
