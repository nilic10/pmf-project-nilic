package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object representing the My Profile page.
 */
public class MyProfilePage extends BasePage {

    private By profileEmail = By.id("profileEmail");
    private By profileFirstname = By.id("profileFirstname");
    private By profileLastname = By.id("profileLastname");

    public MyProfilePage(WebDriver driver) {
        super(driver);
    }

    /**
     * Gets the displayed email on the profile page.
     * @return String email.
     */
    public String getEmail() {
        return getText(profileEmail);
    }
}
