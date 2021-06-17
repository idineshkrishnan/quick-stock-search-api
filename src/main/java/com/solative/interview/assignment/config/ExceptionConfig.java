package com.solative.interview.assignment.config;

import com.solative.interview.assignment.error.ErrorMessage;
import com.solative.interview.assignment.error.QuickStockSearchException;
import com.solative.interview.assignment.utils.ApplicationConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Dinesh Krishnan
 */

@ControllerAdvice
public class ExceptionConfig {

    @ExceptionHandler(value = {QuickStockSearchException.class})
    public ResponseEntity<ErrorMessage> handleException(QuickStockSearchException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getCode(), exception.getMessage());
        if(exception.getCode().equals(ApplicationConstants.INVALID_INPUT_ERROR_CODE)) {
            return ResponseEntity.badRequest().body(errorMessage);
        } else {
            return ResponseEntity.internalServerError().body(errorMessage);
        }
    }
}
