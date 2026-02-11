package com.example.rest.models;

import lombok.*;

/**
 * Data model representing a File.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {
    /** Name of the file. */
    private String name;
    /** Size of the file in bytes. */
    private Double size;
    /** Timestamp of the last modification. */
    private String lastModified;
}
