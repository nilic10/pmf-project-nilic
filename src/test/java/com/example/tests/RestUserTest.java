package com.example.tests;

import com.example.rest.common.users.UserClient;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class RestUserTest {

    private static final String BASE_URL = "http://localhost:3000/api";

    @BeforeAll
    public static void setup() {
        UserClient.init(BASE_URL);
    }

    @Test
    public void getUsersByIdTest() {
        // Koristimo Mosesa sa ID-jem 1 koji provereno postoji u bazi
        Object userId = 1;
        
        UserClient.findById(userId)
                .verifyId(userId)
                .verifyFirstname("Moses");
    }
}
