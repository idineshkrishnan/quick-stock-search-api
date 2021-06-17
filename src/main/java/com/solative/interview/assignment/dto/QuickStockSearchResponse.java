package com.solative.interview.assignment.dto;

import com.google.gson.JsonObject;

/**
 * @author Dinesh Krishnan
 */

public class QuickStockSearchResponse {

    private JsonObject companyData;
    private JsonObject stockData;

    public QuickStockSearchResponse() {

    }

    public QuickStockSearchResponse(JsonObject companyData, JsonObject stockData) {
        this.companyData = companyData;
        this.stockData = stockData;
    }

    public void setCompanyData(JsonObject companyData) {
        this.companyData = companyData;
    }

    public void setStockData(JsonObject stockData) {
        this.stockData = stockData;
    }

    public JsonObject getCompanyData() {
        return companyData;
    }

    public JsonObject getStockData() {
        return stockData;
    }
}
