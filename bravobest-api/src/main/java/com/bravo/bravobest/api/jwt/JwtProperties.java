package com.bravo.bravobest.api.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @ClassName JwtProperties
 * @Description
 * @Author chengfeng
 * @Date 2/5/21 3:10 PM
 **/
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties implements Serializable {

    private static final long serialVersionUID = 1904959780931910861L;

    private String tokenHeader;
    private String secret;
    private Long expiration;
    private String tokenHead;

    public String getTokenHeader() {
        return tokenHeader;
    }

    public JwtProperties setTokenHeader(String tokenHeader) {
        this.tokenHeader = tokenHeader;
        return this;
    }

    public String getSecret() {
        return secret;
    }

    public JwtProperties setSecret(String secret) {
        this.secret = secret;
        return this;
    }

    public Long getExpiration() {
        return expiration;
    }

    public JwtProperties setExpiration(Long expiration) {
        this.expiration = expiration;
        return this;
    }

    public String getTokenHead() {
        return tokenHead;
    }

    public JwtProperties setTokenHead(String tokenHead) {
        this.tokenHead = tokenHead;
        return this;
    }

}
