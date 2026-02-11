package com.example.rest.common.users;

import com.example.rest.BaseRest;
import com.example.rest.models.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

/**
 * Client for interacting with the collection of all users.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AllUsersClient extends BaseRest<User> {

    private User[] users;

    /**
     * Retrieves all users from the API.
     * 
     * @return This AllUsersClient instance with the retrieved users.
     */
    public AllUsersClient getAll() {
        users = this.get("/users", User[].class).getBody();
        return this;
    }

    /**
     * Verifies that the first name of a user with a specific ID matches the expected first name.
     * 
     * @param id The ID of the user to verify.
     * @param expectedFirstname The expected first name.
     * @return This AllUsersClient instance.
     * @throws AssertionError if the user is not found.
     */
    public AllUsersClient verifyUsernameById(Object id, String expectedFirstname) {
        User user = Arrays.stream(this.users)
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new AssertionError("User with ID " + id + " not found"));

        Assertions.assertEquals(expectedFirstname, user.getFirstname(), "Firstname mismatch for user ID " + id);
        return this;
    }
}
