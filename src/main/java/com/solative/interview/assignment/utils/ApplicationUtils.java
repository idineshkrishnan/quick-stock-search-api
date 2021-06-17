package com.solative.interview.assignment.utils;

import com.solative.interview.assignment.error.QuickStockSearchException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import static com.solative.interview.assignment.utils.ApplicationConstants.INVALID_INPUT_ERROR;
import static com.solative.interview.assignment.utils.ApplicationConstants.INVALID_INPUT_ERROR_CODE;

/**
 * @author Dinesh Krishnan
 */

public class ApplicationUtils {

    private static final Logger log = LoggerFactory.getLogger(ApplicationUtils.class);

    public static String buildUrl(final MultiValueMap<String, String> queryParamsMap) {
        log.info("[Start]: [buildUrl][ApplicationUtils]");
        if(queryParamsMap != null && !queryParamsMap.isEmpty()) {
            String url = UriComponentsBuilder
                    .fromUriString(ApplicationConstants.BASE_PATH)
                    .queryParams(queryParamsMap)
                    .build()
                    .toUriString();
            log.info("[End]: [buildUrl][ApplicationUtils]{}", url);
            return url;
        } else {
            throw new QuickStockSearchException(INVALID_INPUT_ERROR_CODE, INVALID_INPUT_ERROR);
        }
    }

    public static String prepareUrl(final String function, final String value) {
        final MultiValueMap<String, String> queryParamsMap = new LinkedMultiValueMap();
        queryParamsMap.set(ApplicationConstants.API_KEY, ApplicationConstants.API_SECRET_KEY);

        if(function.equalsIgnoreCase(ApplicationConstants.TIME_SERIES_INTRADAY)) {
            queryParamsMap.set(ApplicationConstants.FUNCTION, function);
            queryParamsMap.set(ApplicationConstants.SYMBOL, value);
            queryParamsMap.set(ApplicationConstants.INTERVAL, ApplicationConstants.ONE_MIN);
        } else if(function.equalsIgnoreCase(ApplicationConstants.OVERVIEW)) {
            queryParamsMap.set(ApplicationConstants.FUNCTION, function);
            queryParamsMap.set(ApplicationConstants.SYMBOL, value);
        } else {
            queryParamsMap.set(ApplicationConstants.FUNCTION, function);
            queryParamsMap.set(ApplicationConstants.KEYWORDS, value);
        }
        return buildUrl(queryParamsMap);
    }
}
