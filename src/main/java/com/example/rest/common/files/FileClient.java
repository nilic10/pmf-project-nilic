package com.example.rest.common.files;

import com.example.rest.BaseRest;
import com.example.rest.models.File;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

@Data
@EqualsAndHashCode(callSuper = false)
public class FileClient extends BaseRest<File> {

    public FileClient() {
        super();
    }

    public FileClient(File data) {
        super(data);
    }

    public FileClient verifyName(String expectedName) {
        Assertions.assertEquals(expectedName, data.getName(), "File name mismatch");
        return this;
    }

    public FileClient verifySize(Double expectedSize) {
        Assertions.assertEquals(expectedSize, data.getSize(), "File size mismatch");
        return this;
    }

    public FileClient verifyLastModified(String expectedLastModified) {
        Assertions.assertEquals(expectedLastModified, data.getLastModified(), "Last modified mismatch");
        return this;
    }
}
