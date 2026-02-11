package com.example.rest.common.files;

import com.example.rest.BaseRest;
import com.example.rest.models.File;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * Client for interacting with collections of files.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AllFilesClient extends BaseRest<File> {

    private File[] files;

    /**
     * Retrieves all files uploaded by the authenticated user.
     * 
     * @return This AllFilesClient instance with the retrieved files.
     */
    public AllFilesClient getUploadedFiles() {
        this.files = this.get("/files/uploaded", File[].class).getBody();
        return this;
    }

    /**
     * Retrieves all public uploaded files.
     * 
     * @return This AllFilesClient instance with the retrieved public files.
     */
    public AllFilesClient getPublicFiles() {
        this.files = this.get("/files/uploaded/public", File[].class).getBody();
        return this;
    }

    /**
     * Verifies the size of a file identified by its name.
     * 
     * @param name The name of the file to verify.
     * @param expectedFileSize The expected file size in bytes.
     * @return This AllFilesClient instance.
     * @throws AssertionError if the file is not found.
     */
    public AllFilesClient verifyFileSizeByName(String name, Double expectedFileSize) {
        File file = Arrays.stream(this.files)
                .filter(f -> f.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new AssertionError("File with name " + name + " not found"));

        Assertions.assertEquals(expectedFileSize, file.getSize(), "File size mismatch for file " + name);
        return this;
    }
}
