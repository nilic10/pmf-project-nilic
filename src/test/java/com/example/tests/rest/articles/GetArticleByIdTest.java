package com.example.tests.rest.articles;

import com.example.rest.RestClient;
import com.example.rest.common.articles.ArticleClient;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("REST API Tests")
@Feature("Article Management")
public class GetArticleByIdTest extends RestClient {

    private static final Object ARTICLE_ID = 1;
    private static final String ARTICLE_TITLE = "How to write effective test cases";

    @Test
    @DisplayName("Get article by ID - Positive Test")
    public void getArticleByIdTest() {
        findArticleById(ARTICLE_ID)
                .verifyId(ARTICLE_ID)
                .verifyTitle(ARTICLE_TITLE);
    }
}
