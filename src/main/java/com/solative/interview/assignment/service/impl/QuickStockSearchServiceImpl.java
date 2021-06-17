package com.solative.interview.assignment.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.solative.interview.assignment.dto.CompanyInfoResponse;
import com.solative.interview.assignment.dto.CompanyStockDataResponse;
import com.solative.interview.assignment.dto.QuickStockSearchResponse;
import com.solative.interview.assignment.dto.StockSearchResponse;
import com.solative.interview.assignment.error.QuickStockSearchException;
import com.solative.interview.assignment.service.QuickStockSearchService;
import com.solative.interview.assignment.utils.ApplicationConstants;
import com.solative.interview.assignment.utils.ApplicationUtils;
import com.solative.interview.assignment.utils.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import static com.solative.interview.assignment.utils.ApplicationConstants.*;

/**
 * @author Dinesh Krishnan
 */

@Service
public class QuickStockSearchServiceImpl implements QuickStockSearchService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private Gson gson;

    private final Logger log = LoggerFactory.getLogger(QuickStockSearchServiceImpl.class);

    /**
     * This method will fetch both company and stock information by symbol
     * @param symbol
     * @return QuickStockSearchResponse
     */
    @Override
    public QuickStockSearchResponse getCompanyAndStockData(final String symbol) {
        try {
            log.info("[Start]: [getCompanyAndStockData][QuickStockSearchServiceImpl]");
            if (ValidatorUtils.isEmpty(symbol)) {

                // Invoke both API simultaneously
                Future<CompanyInfoResponse> companyInfoResponseFuture = getCompanyInfo(symbol);
                Future<CompanyStockDataResponse> companyStockDataResponseFuture = getCompanyStockData(symbol);

                // Preparing the response object
                QuickStockSearchResponse quickStockSearchResponse = new QuickStockSearchResponse();
                quickStockSearchResponse.setStockData(companyStockDataResponseFuture.get().getResponse());
                quickStockSearchResponse.setCompanyData(companyInfoResponseFuture.get().getResponse());

                log.info("[End]: [getCompanyAndStockData][QuickStockSearchServiceImpl]");
                return quickStockSearchResponse;
            } else {
                log.error("[Error]: [getCompanyAndStockData][QuickStockSearchServiceImpl]");
                throw new QuickStockSearchException(INVALID_INPUT_ERROR_CODE, INVALID_INPUT_ERROR);
            }
        } catch (Exception e) {
            log.error("[Error]: [getCompanyAndStockData][QuickStockSearchServiceImpl]");
            throw new QuickStockSearchException(SERVER_ERROR_CODE, SERVER_ERROR);
        }
    }

    /**
     * This method will fetch daily stock quote information by symbol
     * @param symbol
     * @return CompletableFuture<CompanyStockDataResponse>
     */
    @Async
    @Override
    public CompletableFuture<CompanyStockDataResponse> getCompanyStockData(final String symbol) {
        try {
            log.info("[Start]: [getCompanyStockData][QuickStockSearchServiceImpl]");
            if (ValidatorUtils.isEmpty(symbol)) {

                // Call third party API to get daily stock quote data
                ResponseEntity<String> responseObj = restTemplate
                        .getForEntity(ApplicationUtils.prepareUrl(ApplicationConstants.TIME_SERIES_INTRADAY, symbol), String.class);

                // Preparing response object - converting response to JsonObject
                JsonObject response = gson.fromJson(responseObj.getBody(), JsonObject.class);

                log.info("[End]: [getCompanyStockData][QuickStockSearchServiceImpl]");
                return CompletableFuture.completedFuture(new CompanyStockDataResponse(response));
            } else {
                log.error("[Error]: [getCompanyStockData][QuickStockSearchServiceImpl]");
                throw new QuickStockSearchException(INVALID_INPUT_ERROR_CODE, INVALID_INPUT_ERROR);
            }
        } catch (Exception e) {
            log.error("[Error]: [getCompanyStockData][QuickStockSearchServiceImpl]");
            throw new QuickStockSearchException(SERVER_ERROR_CODE, SERVER_ERROR);
        }
    }

    /**
     * This method will fetch company information by symbol
     * @param symbol
     * @return CompletableFuture<CompanyInfoResponse>
     */
    @Async
    @Override
    public CompletableFuture<CompanyInfoResponse> getCompanyInfo(final String symbol) {
        try {
            log.info("[Start]: [getCompanyInfo][QuickStockSearchServiceImpl]");
            if (ValidatorUtils.isEmpty(symbol)) {

                // Call third party API to get Company information
                ResponseEntity<String> responseObj = restTemplate
                        .getForEntity(ApplicationUtils.prepareUrl(ApplicationConstants.OVERVIEW, symbol), String.class);

                // Preparing response object - converting response to JsonObject
                JsonObject response = gson.fromJson(responseObj.getBody(), JsonObject.class);

                log.info("[End]: [getCompanyInfo][QuickStockSearchServiceImpl]");
                return CompletableFuture.completedFuture(new CompanyInfoResponse(response));
            } else {
                log.error("[Error]: [getCompanyInfo][QuickStockSearchServiceImpl]");
                throw new QuickStockSearchException(INVALID_INPUT_ERROR_CODE, INVALID_INPUT_ERROR);
            }
        } catch (Exception e) {
            log.error("[Error]: [getCompanyInfo][QuickStockSearchServiceImpl]");
            throw new QuickStockSearchException(SERVER_ERROR_CODE, SERVER_ERROR);
        }
    }

    @Override
    public StockSearchResponse searchStock(final String query) {
        try {
            log.info("[Start]: [searchStock][QuickStockSearchServiceImpl]");
            if (ValidatorUtils.isEmpty(query)) {

                // Call third party API to get possible stocks and company based on search term
                ResponseEntity<String> responseObj = restTemplate
                        .getForEntity(ApplicationUtils.prepareUrl(ApplicationConstants.SYMBOL_SEARCH, query), String.class);

                // Preparing response object - converting response to JsonObject
                JsonObject response = gson.fromJson(responseObj.getBody(), JsonObject.class);

                log.info("[End]: [searchStock][QuickStockSearchServiceImpl]");
                return new StockSearchResponse(response);
            } else {
                log.error("[Error]: [searchStock][QuickStockSearchServiceImpl]");
                throw new QuickStockSearchException(INVALID_INPUT_ERROR_CODE, INVALID_INPUT_ERROR);
            }
        } catch (Exception e) {
            log.error("[Error]: [searchStock][QuickStockSearchServiceImpl]");
            throw new QuickStockSearchException(SERVER_ERROR_CODE, SERVER_ERROR);
        }
    }
}
