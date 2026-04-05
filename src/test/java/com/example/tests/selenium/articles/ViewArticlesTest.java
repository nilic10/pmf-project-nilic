package com.example.tests.selenium.articles;

import com.example.rest.common.users.UserClient;
import com.example.rest.models.User;
import com.example.selenium.base.BaseTest;
import com.example.selenium.pages.LoginPage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class ViewArticlesTest extends BaseTest {

    private String email;
    private final String password = "password123!";
    private User testUser;

    @BeforeEach
    public void setUp() {
        
        String uniqueSuffix = UUID.randomUUID().toString().substring(0, 8);
        email = "article_viewer_" + uniqueSuffix + "@example.com";
        
        testUser = User.builder()
                .email(email)
                .firstname("Article")
                .lastname("Viewer")
                .password(password)
                .avatar("https://example.com/avatar.jpg")
                .build();
        
        UserClient userClient = new UserClient();
        userClient.create(testUser);
    }

    @Test
    @DisplayName("View articles page")
    public void testViewArticles() {
        driver.get("http://localhost:3000/login");
        
        new LoginPage(driver)
                .login(email, password)
                .goToArticles()
                .verifyArticlesPageIsDisplayed();
    }
}
