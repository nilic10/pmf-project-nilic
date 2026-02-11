package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UploadPage extends BasePage {

    private By fileInput = By.id("dragdropfile");
    private By uploadButton = By.id("uploadButton");
    private By dragAndDropArea = By.className("drag-area");
    private By alertPopup = By.id("alertPopup");

    public UploadPage(WebDriver driver) {
        super(driver);
    }

    public UploadPage selectFile(String filePath) {
        type(fileInput, filePath);
        return this;
    }

    public UploadPage clickUpload() {
        click(uploadButton);
        return this;
    }

    public UploadPage verifyUploadPageIsDisplayed() {
        verifyElementDisplayed(dragAndDropArea, "Upload page is not displayed (Drag & Drop area not found: " + dragAndDropArea + ")");
        return this;
    }

    public UploadPage verifyAlertMessage(String expectedMessage) {
        verifyText(alertPopup, expectedMessage, "Alert message is not correct. Expected: '" + expectedMessage + "' at " + alertPopup);
        return this;
    }
}
