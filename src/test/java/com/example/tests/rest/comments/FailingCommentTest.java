package com.example.tests.rest.comments;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("Comment Management")
/**
 * Test suite for intentionally failing comment scenarios.
 * Demonstrates how the test framework handles API errors for comment retrieval.
 */
public class FailingCommentTest extends RestClient {

    /**
     * Tests retrieval of a non-existent comment without expecting an exception.
     * This test is expected to fail, demonstrating a 404 response handling.
     */
    @Test
    @DisplayName("Failing Comment Test - Get Non-existent Comment (404)")
    public void failingCommentTest() {
        findCommentById(999999);
    }
}
