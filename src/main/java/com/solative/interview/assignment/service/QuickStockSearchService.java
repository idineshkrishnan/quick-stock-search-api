package com.solative.interview.assignment.service;

import com.solative.interview.assignment.dto.CompanyInfoResponse;
import com.solative.interview.assignment.dto.CompanyStockDataResponse;
import com.solative.interview.assignment.dto.QuickStockSearchResponse;
import com.solative.interview.assignment.dto.StockSearchResponse;

import java.util.concurrent.CompletableFuture;

/**
 * @author Dinesh Krishnan
 */

public interface QuickStockSearchService {

    QuickStockSearchResponse getCompanyAndStockData(final String symbol);
    CompletableFuture<CompanyStockDataResponse> getCompanyStockData(final String symbol);
    CompletableFuture<CompanyInfoResponse> getCompanyInfo(final String symbol);
    StockSearchResponse searchStock(final String query);
}
