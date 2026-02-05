package com.example.rest.models;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class File {
    private String name;
    private Double size;
    private String lastModified;
}
