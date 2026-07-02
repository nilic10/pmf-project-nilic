package com.example.tests.rest.comments;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.HttpClientErrorException;

@Epic("REST API Tests")
@Feature("Comment Management")
/**
 * Test suite for verifying authorization requirements when deleting comments.
 * Verifies that unauthenticated delete requests are rejected with a 401 Unauthorized response.
 */
public class DeleteCommentWithoutAuthTest extends RestClient {

    private static final Object COMMENT_ID = 1;

    /**
     * Tests that attempting to delete a comment without authentication throws a 401 Unauthorized exception.
     */
    @Test
    @DisplayName("Delete comment without auth - Negative Test")
    public void deleteCommentWithoutAuthTest() {
        Assertions.assertThrows(HttpClientErrorException.Unauthorized.class, () -> {
            deleteComment(COMMENT_ID);
        }, "Should throw 401 Unauthorized for deleting comment without token");
    }
}
