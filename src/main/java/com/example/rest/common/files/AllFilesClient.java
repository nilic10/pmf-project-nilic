package com.example.rest.common.files;

import com.example.rest.BaseRest;
import com.example.rest.models.File;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

@Data
@EqualsAndHashCode(callSuper = false)
public class AllFilesClient extends BaseRest<File> {

    private File[] files;

    public AllFilesClient getUploadedFiles() {
        this.files = this.get("/files/uploaded", File[].class).getBody();
        return this;
    }

    public AllFilesClient getPublicFiles() {
        this.files = this.get("/files/uploaded/public", File[].class).getBody();
        return this;
    }

    public AllFilesClient verifyFileSizeByName(String name, Double expectedFileSize) {
        File file = Arrays.stream(this.files)
                .filter(f -> f.getName().equals(name))
                .findFirst()
                .orElseThrow(() -> new AssertionError("File with name " + name + " not found"));

        Assertions.assertEquals(expectedFileSize, file.getSize(), "File size mismatch for file " + name);
        return this;
    }
}
