package com.example.tests.rest.users;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("User Management")
public class GetUserByIdTest extends RestClient {

    private static final Object USER_ID = 1;
    private static final String FIRSTNAME = "Moses";

    @Test
    @DisplayName("Get user by ID - Positive Test")
    public void getUserByIdTest() {
        findUserById(USER_ID)
                .verifyId(USER_ID)
                .verifyFirstname(FIRSTNAME);
    }
}
