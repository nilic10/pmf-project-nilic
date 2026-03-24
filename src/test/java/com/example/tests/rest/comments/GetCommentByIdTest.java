package com.example.tests.rest.comments;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("Comment Management")
public class GetCommentByIdTest extends RestClient {

    private static final Object COMMENT_ID = 1;
    private static final String COMMENT_BODY = "I loved your insights on usability testing. It's crucial to ensure that the software meets the needs of the end users. Have you encountered any interesting user feedback during usability testing that led to significant improvements in the product?";

    @Test
    @DisplayName("Get comment by ID - Positive Test")
    public void getCommentByIdTest() {
        findCommentById(COMMENT_ID)
                .verifyId(COMMENT_ID)
                .verifyBody(COMMENT_BODY);
    }
}
