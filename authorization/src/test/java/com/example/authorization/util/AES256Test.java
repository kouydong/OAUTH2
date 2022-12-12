package com.example.authorization.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AES256Test {

    @Test
    @DisplayName("암호화테스트")
    public void decrypt() throws Exception {
        String clientId = "SAMSUNGCNT";
        AES256 aes256 = new AES256();
        System.out.print(aes256.encrypt(clientId));

    }

}