package com.example.rest.models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginData {
    private String email;
    private String password;
}
