package com.easy.work.user.model;

/**
 * Created by apple on 18/4/16.
 */
public class TokenModel {

    private String token;

    private String refreshToken;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
