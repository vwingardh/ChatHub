package com.chat.chat.customApiResponses;

public class ApiResponse {

    private Object data;
    private String error;
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
