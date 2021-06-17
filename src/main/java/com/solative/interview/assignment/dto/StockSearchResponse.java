package com.solative.interview.assignment.dto;

import com.google.gson.JsonObject;

/**
 * @author Dinesh Krishnan
 */

public class StockSearchResponse {

    private JsonObject data;

    public StockSearchResponse(JsonObject data) {
        this.data = data;
    }

    public JsonObject getData() {
        return data;
    }

    public void setData(JsonObject data) {
        this.data = data;
    }
}
