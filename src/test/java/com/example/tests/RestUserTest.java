package com.example.tests;

import com.example.rest.BaseRest;
import com.example.rest.common.users.UserClient;
import org.junit.jupiter.api.Test;

public class RestUserTest extends UserClient {

    @Test
    public void getUsersByIdTest() {
        // Koristimo Mosesa sa ID-jem 1 koji provereno postoji u bazi
        Object userId = 1;
        
        findById(userId)
                .verifyId(userId)
                .verifyFirstname("Moses");
    }
}
