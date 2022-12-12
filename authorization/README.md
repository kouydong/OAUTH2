![Java_17](https://img.shields.io/badge/java-v17-red?logo=java)

Relating to Oauth2 dependencies <br>
![Spring_Boot](https://img.shields.io/badge/Spring_Boot-v2.7.6-green.svg?logo=spring)
![Spring_Boot](https://img.shields.io/badge/jsonwebtoken-v0.11.5-green.svg?logo=spring)

Author : kouydong

# Authorization server with using the JWT

## Overview
- Implementing the authorization server

## Explanation
JWT 토큰은 Header, Payload, Signature 부분으로 분리 됨

| 영역        | Contents                            |
|-----------|-------------------------------------|
| Header    | 알고리즘 및 토큰타입                         |
| Payload   | 토큰 발급자, 토큰제목, 토큰 대상자, 토큰의 만료시간 등 포함 |
| Signature | 유효성 검증에 사용                          |

## Environment
- Java version : 17
- Spring-boot version 2.7.6
- DBS : JPA, H2, MariaDB
- IDE tool : Intellij Community

## Process flow
- Firstly, creating the JWT after sending headers(client, clientSecret)
- http://localhost:8080/api/common/token

- 
- 

## Oauth2 Authorize Code 인증 URI
- http://localhost:8081/oauth/authorize?client_id=testClientId&redirect_uri=http://localhost:8081/oauth2/callback&response_type=code&scope=read