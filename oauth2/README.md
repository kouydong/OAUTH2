![Java_17](https://img.shields.io/badge/java-v17-red?logo=java)

Relating to Oauth2 dependencies <br>
![Spring_Boot](https://img.shields.io/badge/Spring_Boot-v2.7.6-green.svg?logo=spring)
![Spring_Cloud_Starter_Config](https://img.shields.io/badge/Spring_Cloud_Starter_Config-v3.1.5-green.svg?logo=spring)
![Spring_Cloud_Security](https://img.shields.io/badge/Spring_Cloud_Security-v2.2.5-green.svg?logo=spring)
![Spring_Cloud_Starter_Oauth2](https://img.shields.io/badge/Spring_Cloud_Starter_Oauth2-v2.2.5-green.svg?logo=spring)


# Oauth2 AuthorizationServer(인증서버) 구축 
Author : kouydong

## 개요
- Spring-Boot 제공하는 Oauth2 인증서버 구축



## Environment
- Java version : 17
- Spring-boot version 2.7.6
- DBS : JPA, H2(필요 시 향후 적용 예정)
- IDE tool : Intellij Community
- Spring Oauth2
 
## Process flow 
- Spring Boot Oauth2 – AuthorizationServer : DB처리, JWT 토큰 방식 적용
    - Document
        - 인증 URI를 호출한다. 
        - inMemory상에 등록되어 있는 user 및 pass를 입력한다.
        - 사용자 정보 제공 동의 여부를 체크 한다.
        - Access Token을 발급받는다.(미구현)

## Oauth2 Authorize Code 인증 URI
- http://localhost:8081/oauth/authorize?client_id=testClientId&redirect_uri=http://localhost:8081/oauth2/callback&response_type=code&scope=read