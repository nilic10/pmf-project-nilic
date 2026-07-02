package com.example.tests.rest.users;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("User Management")
/**
 * Test suite for retrieving a user by their ID via REST API.
 * Verifies that an existing user can be fetched and their data matches expected values.
 */
public class GetUserByIdTest extends RestClient {

    private static final Object USER_ID = 1;
    private static final String FIRSTNAME = "Moses";

    /**
     * Tests that a user with a known ID is returned with the correct ID and first name.
     */
    @Test
    @DisplayName("Get user by ID - Positive Test")
    public void getUserByIdTest() {
        findUserById(USER_ID)
                .verifyId(USER_ID)
                .verifyFirstname(FIRSTNAME);
    }
}
