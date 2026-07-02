package com.example.tests.rest.comments;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("Comment Management")
/**
 * Test suite for intentionally failing comment delete scenarios.
 * Demonstrates unauthorized delete attempt behavior without an authentication token.
 */
public class FailingDeleteCommentTest extends RestClient {

    private static final Object COMMENT_ID = 1;

    /**
     * Tests deletion of a comment without an authentication token.
     * This test is expected to fail, demonstrating a 401 Unauthorized response.
     */
    @Test
    @DisplayName("Failing Delete Comment Test - Unauthorize (401)")
    public void failingDeleteCommentTest() {
        deleteComment(COMMENT_ID);
    }
}
