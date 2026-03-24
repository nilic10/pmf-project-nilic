package com.example.tests.rest.users;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

@Epic("REST API Tests")
@Feature("User Management")
public class DeleteNonExistentUserTest extends RestClient {

    private static final Object NON_EXISTENT_ID = 99999;

    @Test
    @DisplayName("Delete non-existent user - Negative Test")
    public void deleteNonExistentUserTest() {
        Assertions.assertThrows(HttpClientErrorException.Unauthorized.class, () -> {
            deleteUser(NON_EXISTENT_ID);
        }, "Should throw 401 Unauthorized for deleting without token");
    }
}
