package com.example.tests.rest.articles;

import com.example.rest.RestClient;
import com.example.rest.common.articles.ArticleClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("Article Management")
/**
 * Test suite for retrieving an article by its ID via REST API.
 * Verifies that an existing article can be fetched and its data matches expected values.
 */
public class GetArticleByIdTest extends RestClient {

    private static final Object ARTICLE_ID = 1;
    private static final String ARTICLE_TITLE = "How to write effective test cases";

    /**
     * Tests that an article with a known ID is returned with the correct ID and title.
     */
    @Test
    @DisplayName("Get article by ID - Positive Test")
    public void getArticleByIdTest() {
        findArticleById(ARTICLE_ID)
                .verifyId(ARTICLE_ID)
                .verifyTitle(ARTICLE_TITLE);
    }
}
