package com.example.rest.common.users;

import com.example.rest.BaseRest;
import com.example.rest.models.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

@Data
@EqualsAndHashCode(callSuper = false)
public class UsersClient extends BaseRest<User> {

    private User[] users;

    public UsersClient getAll() {
        users = this.get("/users", User[].class).getBody();
        return this;
    }

    public UsersClient verifyUsernameById(Object id, String expectedFirstname) {
        User user = Arrays.stream(this.users)
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new AssertionError("User with ID " + id + " not found"));

        Assertions.assertEquals(expectedFirstname, user.getFirstname(), "Firstname mismatch for user ID " + id);
        return this;
    }
}
