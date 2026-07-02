package com.example.tests.rest.files;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Epic("REST API Tests")
@Feature("File Management")
/**
 * Test suite for retrieving public files via REST API.
 * Verifies the API behavior when requesting the public files endpoint.
 */
public class GetPublicFilesTest extends RestClient {

    /**
     * Tests that requesting the public files endpoint returns a 404 Not Found response.
     */
    @Test
    @DisplayName("Get public files - Negative Test")
    public void getPublicFilesTest() {
        assertThrows(NotFound.class, () -> {
            getPublicFiles();
        });
    }
}
