package com.example.rest.common.files;

import com.example.rest.BaseRest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

@Data
@EqualsAndHashCode(callSuper = false)
public class FileClient extends BaseRest<FileClient> {
    private String fileName;
    private String url;

    public FileClient() {
        super();
    }

    public FileClient(FileClient data) {
        super(data);
        if (data != null) {
            this.fileName = data.getFileName();
            this.url = data.getUrl();
        }
    }

    public void setFileToken(String token) {
        this.setToken(token);
    }

    public FileClient[] getUploadedFiles() {
        FileClient[] files = this.get("/files/uploaded", FileClient[].class).getBody();
        if (files == null) return new FileClient[0];
        for (FileClient file : files) {
            file.setData(file); // Mapiramo podatke sami sebi jer je FileClient i model
        }
        return files;
    }

    public FileClient[] getPublicFiles() {
        FileClient[] files = this.get("/files/uploaded/public", FileClient[].class).getBody();
        if (files == null) return new FileClient[0];
        for (FileClient file : files) {
            file.setData(file);
        }
        return files;
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
