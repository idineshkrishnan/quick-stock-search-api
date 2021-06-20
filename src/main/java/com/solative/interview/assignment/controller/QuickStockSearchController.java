package com.solative.interview.assignment.controller;

import com.solative.interview.assignment.dto.QuickStockSearchResponse;
import com.solative.interview.assignment.dto.StockSearchResponse;
import com.solative.interview.assignment.service.QuickStockSearchService;
import com.solative.interview.assignment.utils.ApplicationConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author Dinesh Krishnan
 */

@RestController
@RequestMapping(value = {"/api"})
public class QuickStockSearchController {

    private final QuickStockSearchService quickStockSearchService;

    private QuickStockSearchController(QuickStockSearchService quickStockSearchService) {
        this.quickStockSearchService = quickStockSearchService;
    }

    @CrossOrigin(origins = ApplicationConstants.APP_ALLOWED_ORIGIN)
    @GetMapping(value = {"/stock"})
    public ResponseEntity<QuickStockSearchResponse> getTodayData(@RequestParam("symbol") String symbol) {
        return ResponseEntity.ok().body(quickStockSearchService.getCompanyAndStockData(symbol));
    }

    @CrossOrigin(origins = ApplicationConstants.APP_ALLOWED_ORIGIN)
    @GetMapping(value = {"/search"})
    public ResponseEntity<StockSearchResponse> searchStock(@RequestParam("query") String query) {
        return ResponseEntity.ok().body(quickStockSearchService.searchStock(query));
    }
}
