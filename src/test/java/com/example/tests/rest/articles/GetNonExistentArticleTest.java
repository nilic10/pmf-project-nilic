package com.example.tests.rest.articles;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

@Epic("REST API Tests")
@Feature("Article Management")
/**
 * Test suite for handling requests for non-existent articles via REST API.
 * Verifies that the API returns a 404 Not Found response for missing resources.
 */
public class GetNonExistentArticleTest extends RestClient {

    private static final Object NON_EXISTENT_ID = 99999;

    /**
     * Tests that fetching an article with a non-existent ID throws a 404 Not Found exception.
     */
    @Test
    @DisplayName("Get non-existent article - Negative Test")
    public void getNonExistentArticleTest() {
        Assertions.assertThrows(HttpClientErrorException.NotFound.class, () -> {
            findArticleById(NON_EXISTENT_ID);
        }, "Should throw 404 Not Found for non-existent article ID");
    }
}
