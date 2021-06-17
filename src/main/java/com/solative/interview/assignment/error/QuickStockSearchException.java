package com.solative.interview.assignment.error;

/**
 * @author Dinesh Krishnan
 */

public class QuickStockSearchException extends RuntimeException {

    private Integer code;
    private String message;

    public QuickStockSearchException() {

    }

    public QuickStockSearchException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
