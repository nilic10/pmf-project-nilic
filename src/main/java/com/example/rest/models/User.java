package com.example.rest.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Object id;
    private String email;
    private String firstname;
    private String lastname;
    private String password;
    private String avatar;
}
