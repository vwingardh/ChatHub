package com.chat.chat.user;

public class ApiResponse {

    private Object data;
    private String error;

    public ApiResponse() {

    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
