package com.example.tests.rest;

import com.example.rest.RestClient;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("User Management")
public class RestUserTest extends RestClient {

    @Test
    @Story("Get User by ID")
    @Description("Verifies that a user can be retrieved by their unique ID")
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Test: Get User by ID (Moses)")
    public void getUsersByIdTest() {
        // Koristimo Mosesa sa ID-jem 1 koji provereno postoji u bazi
        Object userId = 1;

        findUserById(userId)
                .verifyId(userId)
                .verifyFirstname("Moses");
    }

    @Test
    public void getAllUsersStreamVerificationTest() {
        getAllUsers()
                .verifyUsernameById(1, "Moses")
                .verifyUsernameById(2, "Kuja");
    }
}
