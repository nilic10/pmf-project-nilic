package com.example.tests.rest.users;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

@Epic("REST API Tests")
@Feature("User Management")
/**
 * Test suite for verifying authorization requirements when deleting users.
 * Verifies that unauthenticated delete requests are rejected with a 401 Unauthorized response.
 */
public class DeleteNonExistentUserTest extends RestClient {

    private static final Object NON_EXISTENT_ID = 99999;

    /**
     * Tests that attempting to delete a user without authentication throws a 401 Unauthorized exception.
     */
    @Test
    @DisplayName("Delete non-existent user - Negative Test")
    public void deleteNonExistentUserTest() {
        assertThrows(HttpClientErrorException.Unauthorized.class, () -> {
            deleteUser(NON_EXISTENT_ID);
        }, "Should throw 401 Unauthorized for deleting without token");
    }
}
