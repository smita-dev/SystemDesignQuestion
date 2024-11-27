package com.tinyurl.tinyUrl.model;

public class UrlDTO {
    private String url;
    private String ExpirationDate;

    public UrlDTO(String url, String ExpirationDate) {
        this.url = url;
        this.ExpirationDate = ExpirationDate;
    }

    public UrlDTO(){

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getExpirationDate() {
        return ExpirationDate;
    }

    public void setExpirationTime(String ExpirationDate) {
        ExpirationDate = ExpirationDate;
    }
}
