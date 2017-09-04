package com.ht.htlibrary.net;

public class ResultException extends RuntimeException {

    private String code;
    private String message = " ";

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResultException(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ResultException{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
