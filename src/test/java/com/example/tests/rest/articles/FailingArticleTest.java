package com.example.tests.rest.articles;

import com.example.rest.RestClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("Article Management")
public class FailingArticleTest extends RestClient {

    @Test
    @DisplayName("Failing Article Test - Get Non-existent Article (404)")
    public void failingArticleTest() {
        findArticleById(999999);
    }
}
