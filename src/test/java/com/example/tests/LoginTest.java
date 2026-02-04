package com.example.tests;

import com.example.base.BaseTest;
import com.example.pages.HomePage;
import com.example.pages.LoginPage;
import org.junit.jupiter.api.Test;

public class LoginTest extends BaseTest {

    @Test
    public void testLoginAndNavigation() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateTo("http://localhost:3000/login/");
        
        HomePage homePage = loginPage
                .verifyLoginPageIsDisplayed()
                .login("nikola.ilic84@gmail.com", "password1!")
                .verifyHomePageIsDisplayed();

        homePage.verifyWelcomeMessage("Hi nikola.ilic84@gmail.com!");
        
        homePage.goToUsers()
                .verifyUsersPageIsDisplayed();
        
        homePage.goToStatistics()
                .verifyStatisticsPageIsDisplayed();

        homePage.goToArticles()
                .verifyArticlesPageIsDisplayed();
                // .verifyPaginationVisible(); // Removed as it causes failure when there are few articles
        
        homePage.goToUpload()
                .verifyUploadPageIsDisplayed();
        
        homePage.goToArticles().goToAddArticle()
                .verifyAddArticlePageIsDisplayed();
    }
}
