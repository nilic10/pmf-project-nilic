package com.example.tests.rest.articles;

import com.example.rest.RestClient;
import com.example.rest.common.articles.ArticleClient;
import com.example.rest.models.Article;
import com.example.rest.common.users.UserClient;
import com.example.rest.models.User;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

@Epic("REST API Tests")
@Feature("Article Management")
public class CreateArticleTest extends RestClient {

    private static final String DEFAULT_PASSWORD = "password1!";
    private static final String DEFAULT_AVATAR = "https://example.com/avatar.jpg";
    private static final String ARTICLE_TITLE = "New Automated Article";
    private static final String ARTICLE_BODY = "This is the content of the automated article.";
    private static final String ARTICLE_DATE = "2024-05-20T10:00:00Z";
    private static final String ARTICLE_IMAGE = "https://example.com/image.jpg";

    private UserClient testUser;
    private String token;

    @BeforeEach
    public void setup() {
        String uniqueEmail = "testuser_" + UUID.randomUUID().toString().substring(0, 8) + "@example.com";

        User newUser = User.builder()
                .email(uniqueEmail)
                .firstname("Test")
                .lastname("User")
                .password(DEFAULT_PASSWORD)
                .avatar(DEFAULT_AVATAR)
                .build();

        testUser = createUser(newUser);

        token = login(uniqueEmail, DEFAULT_PASSWORD);
    }

    @Test
    @DisplayName("Create new article - Positive Test")
    public void createArticleTest() {
        Article newArticle = Article.builder()
                .title(ARTICLE_TITLE)
                .body(ARTICLE_BODY)
                .date(ARTICLE_DATE)
                .image(ARTICLE_IMAGE)
                .user_id(testUser.getData().getId())
                .build();

        createArticle(token, newArticle)
                .verifyTitle(ARTICLE_TITLE)
                .verifyBody(ARTICLE_BODY)
                .verifyUserId(testUser.getData().getId());
    }
}
