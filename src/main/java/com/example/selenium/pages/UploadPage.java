package com.example.selenium.pages;

import com.example.selenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Page object representing the Upload page.
 * Provides methods for selecting files and uploading them.
 */
public class UploadPage extends BasePage {

    private By fileInput = By.id("dragdropfile");
    private By uploadButton = By.id("uploadButton");
    private By dragAndDropArea = By.className("drag-area");
    private By alertPopup = By.id("alertPopup");

    /**
     * Constructor for UploadPage.
     * @param driver WebDriver instance.
     */
    public UploadPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Selects a file for upload by typing the file path into the hidden file input.
     * @param filePath Absolute path to the file.
     * @return This UploadPage instance.
     */
    public UploadPage selectFile(String filePath) {
        type(fileInput, filePath);
        return this;
    }

    /**
     * Clicks the upload button to start the file upload.
     * @return This UploadPage instance.
     */
    public UploadPage clickUpload() {
        click(uploadButton);
        return this;
    }

    /**
     * Verifies that the Upload page is displayed by checking for the drag and drop area.
     * @return This UploadPage instance.
     */
    public UploadPage verifyUploadPageIsDisplayed() {
        verifyElementDisplayed(dragAndDropArea, "Upload page is not displayed (Drag & Drop area not found: " + dragAndDropArea + ")");
        return this;
    }

    /**
     * Verifies the alert message text after an upload attempt.
     * @param expectedMessage The expected message.
     * @return This UploadPage instance.
     */
    public UploadPage verifyAlertMessage(String expectedMessage) {
        verifyText(alertPopup, expectedMessage, "Alert message is not correct. Expected: '" + expectedMessage + "' at " + alertPopup);
        return this;
    }
}
