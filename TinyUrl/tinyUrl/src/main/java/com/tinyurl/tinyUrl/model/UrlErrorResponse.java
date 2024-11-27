package com.tinyurl.tinyUrl.model;


public class UrlErrorResponse {
    private String status;
    private String error;

    public UrlErrorResponse(String status, String error){
        this.status=status;
        this.error=error;
    }

    public UrlErrorResponse(){

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
