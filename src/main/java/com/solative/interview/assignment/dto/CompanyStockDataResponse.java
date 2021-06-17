package com.solative.interview.assignment.dto;

import com.google.gson.JsonObject;

/**
 * @author Dinesh Krishnan
 */

public class CompanyStockDataResponse {

    private JsonObject response;

    public CompanyStockDataResponse(JsonObject response) {
        this.response = response;
    }

    public JsonObject getResponse() {
        return response;
    }

    public void setResponse(JsonObject response) {
        this.response = response;
    }
}
