package com.example.corespring.book;

/**
 * @author zuzhi
 * @date 09/04/2018
 */
public class Error {
    private int code;
    private String message;

    Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
