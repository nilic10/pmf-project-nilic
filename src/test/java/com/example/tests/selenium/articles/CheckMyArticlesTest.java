package com.example.tests.selenium.articles;

import com.example.rest.RestClient;
import com.example.rest.common.articles.ArticleClient;
import com.example.rest.models.Article;
import com.example.rest.common.users.UserClient;
import com.example.rest.models.User;
import com.example.selenium.base.BaseTest;
import com.example.selenium.pages.ArticlesPage;
import com.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.UUID;

public class CheckMyArticlesTest extends BaseTest {

    private String email;
    private final String password = "password123!";
    private static ArticleClient articleClient;

    @BeforeEach
    public void setUp() {

        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        email = "my_articles_user_" + uniqueSuffix + "@example.com";

        User newUser = User.builder()
                .email(email)
                .firstname("My")
                .lastname("Articles")
                .password(password)
                .avatar("https://example.com/avatar.jpg")
                .build();

        RestClient restClient = new RestClient();

        UserClient testClient = restClient.createUser(newUser);
        
        String token = restClient.login(email, password);
        
        Article newArticle = Article.builder()
                .title("My Test Article " + uniqueSuffix)
                .body("Content for my articles test")
                .date("2024-05-20T10:00:00Z")
                .image("https://example.com/image.jpg")
                .user_id(testClient.getData().getId())
                .build();

        articleClient = restClient.createArticle(token, newArticle);
    }

    @Test
    @DisplayName("Check my articles")
    public void testCheckMyArticles() {
        driver.get("http://localhost:3000/login");
        
        new LoginPage(driver)
                .login(email, password)
                .goToMyArticles()
                .verifyArticleWithTitleExists(articleClient.getData().getTitle())
                .verifyArticleCount(1);
    }
}
