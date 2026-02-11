package com.example.rest.models;

import lombok.*;
import lombok.experimental.SuperBuilder;

/**
 * Data model representing a User.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    /** Unique identifier for the user. */
    private Object id;
    /** Email address of the user. */
    private String email;
    /** First name of the user. */
    private String firstname;
    /** Last name of the user. */
    private String lastname;
    /** Password of the user. */
    private String password;
    /** URL or path to the user's avatar image. */
    private String avatar;
}
