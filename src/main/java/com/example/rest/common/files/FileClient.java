package com.example.rest.common.files;

import com.example.rest.BaseRest;
import com.example.rest.models.File;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

/**
 * Client for interacting with file information.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class FileClient extends BaseRest<File> {

    /**
     * Default constructor.
     */
    public FileClient() {
        super();
    }

    /**
     * Constructor with initial file data.
     * 
     * @param data Initial file data.
     */
    public FileClient(File data) {
        super(data);
    }

    /**
     * Verifies that the file name matches the expected name.
     * 
     * @param expectedName The expected file name.
     * @return This FileClient instance.
     */
    public FileClient verifyName(String expectedName) {
        Assertions.assertEquals(expectedName, data.getName(), "File name mismatch");
        return this;
    }

    /**
     * Verifies that the file size matches the expected size.
     * 
     * @param expectedSize The expected size in bytes.
     * @return This FileClient instance.
     */
    public FileClient verifySize(Double expectedSize) {
        Assertions.assertEquals(expectedSize, data.getSize(), "File size mismatch");
        return this;
    }

    /**
     * Verifies that the last modified date/time matches the expected value.
     * 
     * @param expectedLastModified The expected last modified string.
     * @return This FileClient instance.
     */
    public FileClient verifyLastModified(String expectedLastModified) {
        Assertions.assertEquals(expectedLastModified, data.getLastModified(), "Last modified mismatch");
        return this;
    }
}
