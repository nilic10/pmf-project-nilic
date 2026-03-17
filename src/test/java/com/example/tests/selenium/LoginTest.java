package com.example.tests.selenium;

import com.example.selenium.base.BaseTest;
import com.example.selenium.pages.HomePage;
import com.example.selenium.pages.LoginPage;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@Epic("Selenium UI Tests")
@Feature("Login and Navigation")
public class LoginTest extends BaseTest {

    @Test
    @Story("Successful Login and Menu Navigation")
    @Description("Verifies that a user can login and navigate through all main menu items")
    @Severity(SeverityLevel.BLOCKER)
    @DisplayName("Test: Login and Navigation")
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
                .verifyArticlesPageIsDisplayed()
                .verifyPaginationVisible();
        
        homePage.goToUpload()
                .verifyUploadPageIsDisplayed();
        
        homePage.goToArticles().goToAddArticle()
                .verifyAddArticlePageIsDisplayed();
    }
}
