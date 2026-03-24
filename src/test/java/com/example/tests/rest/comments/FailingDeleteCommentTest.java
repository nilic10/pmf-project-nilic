package com.example.tests.rest.comments;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("Comment Management")
public class FailingDeleteCommentTest extends RestClient {

    private static final Object COMMENT_ID = 1;

    @Test
    @DisplayName("Failing Delete Comment Test - Unauthorize (401)")
    public void failingDeleteCommentTest() {
        deleteComment(COMMENT_ID);
    }
}
