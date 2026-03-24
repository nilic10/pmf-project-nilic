package com.example.tests.rest.comments;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("Comment Management")
public class FailingCommentTest extends RestClient {

    @Test
    @DisplayName("Failing Comment Test - Get Non-existent Comment (404)")
    public void failingCommentTest() {
        findCommentById(999999);
    }
}
