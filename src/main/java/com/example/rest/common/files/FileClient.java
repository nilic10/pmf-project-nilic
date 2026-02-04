package com.example.rest.common.files;

import com.example.rest.BaseRest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.Assertions;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileClient {
    private String fileName;
    private String url;

    private static BaseRest<FileClient> rest;

    public static void init(String baseUrl) {
        rest = new BaseRest<FileClient>(baseUrl) {};
    }

    public static void setToken(String token) {
        rest.setToken(token);
    }

    public static FileClient[] getUploadedFiles() {
        return rest.get("/files/uploaded", FileClient[].class).getBody();
    }

    public static FileClient[] getPublicFiles() {
        return rest.get("/files/uploaded/public", FileClient[].class).getBody();
    }

    public FileClient verifyFileName(String expectedName) {
        Assertions.assertEquals(expectedName, fileName, "File name mismatch");
        return this;
    }

    public FileClient verifyUrl(String expectedUrl) {
        Assertions.assertEquals(expectedUrl, url, "URL mismatch");
        return this;
    }
}
