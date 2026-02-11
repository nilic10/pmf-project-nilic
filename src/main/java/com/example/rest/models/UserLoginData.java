package com.example.rest.models;

import lombok.*;

/**
 * Data model for user login credentials.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginData {
    /** User's email address. */
    private String email;
    /** User's password. */
    private String password;
}
