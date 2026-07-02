package com.example.tests.rest.users;

import com.example.rest.RestClient;
import com.example.rest.common.users.UserClient;
import com.example.rest.models.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("User Management")
/**
 * Test suite for intentionally failing user creation scenarios.
 * Demonstrates validation error behavior when required fields are missing.
 */
public class FailingUserTest extends RestClient {

    /**
     * Tests user creation without an email field.
     * This test is expected to fail, demonstrating a validation error response.
     */
    @Test
    @DisplayName("Failing User Test - Validation Error (Missing Email)")
    public void failingUserTest() {
        UserClient userClient = new UserClient();
        userClient.setData(User.builder()
                .firstname("Test")
                .lastname("User")
                .password("password")
               .build());
        userClient.create();
    }
}
