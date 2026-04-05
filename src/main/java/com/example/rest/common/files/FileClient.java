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
     * Creates a new file entry using the provided token and data.
     *
     * @param token The bearer token for authorization.
     * @param data  The file data to create.
     * @return This FileClient instance with created file data.
     */
    public FileClient create(String token, File data) {
        this.setToken(token);
        File responseData = this.post("/files", data, File.class).getBody();
        this.setData(responseData);
        return this;
    }

    /**
     * Updates the file information using PUT (full update).
     *
     * @param token The bearer token for authorization.
     * @param data  The file data to update.
     * @return This FileClient instance with updated data.
     */
    public FileClient update(String token, File data) {
        this.setToken(token);
        File responseData = this.put("/files/" + data.getName(), data, File.class).getBody();
        this.setData(responseData);
        return this;
    }

    /**
     * Updates the file information using PATCH (partial update).
     *
     * @param token The bearer token for authorization.
     * @param data  The file data to patch.
     * @return This FileClient instance with patched data.
     */
    public FileClient patch(String token, File data) {
        this.setToken(token);
        File responseData = this.patch("/files/" + data.getName(), data, File.class).getBody();
        this.setData(responseData);
        return this;
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
