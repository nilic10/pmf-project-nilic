package com.example.tests.rest.articles;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("Article Management")
/**
 * Test suite for intentionally failing article scenarios.
 * Demonstrates how the test framework handles API errors for article retrieval.
 */
public class FailingArticleTest extends RestClient {

    /**
     * Tests retrieval of a non-existent article without expecting an exception.
     * This test is expected to fail, demonstrating a 404 response handling.
     */
    @Test
    @DisplayName("Failing Article Test - Get Non-existent Article (404)")
    public void failingArticleTest() {
        findArticleById(999999);
    }
}
