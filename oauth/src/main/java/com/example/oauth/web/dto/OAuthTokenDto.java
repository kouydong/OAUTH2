package com.example.oauth.web.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * OAuth2 DTO 정의
 * @author kouydong
 */
@Getter
@Setter
public class OAuthTokenDto {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private long expires_in;
    private String scope;
}
