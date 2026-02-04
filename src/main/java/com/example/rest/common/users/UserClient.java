package com.example.rest.common.users;

import com.example.rest.BaseRest;
import com.example.rest.models.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.junit.jupiter.api.Assertions;

@Data
@NoArgsConstructor
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserClient extends User {

    private static BaseRest<UserClient> rest;

    public static void init(String baseUrl) {
        rest = new BaseRest<UserClient>(baseUrl) {};
    }

    public static void setToken(String token) {
        rest.setToken(token);
    }

    public static UserClient findById(Object id) {
        return rest.get("/users/" + id, UserClient.class).getBody();
    }

    public static UserClient[] getAll() {
        return rest.get("/users", UserClient[].class).getBody();
    }

    public UserClient create() {
        return rest.post("/users", this, UserClient.class).getBody();
    }

    public UserClient update() {
        return rest.put("/users/" + this.getId(), this, UserClient.class).getBody();
    }

    public UserClient patch() {
        return rest.patch("/users/" + this.getId(), this, UserClient.class).getBody();
    }

    public static void delete(Object id) {
        rest.delete("/users/" + id);
    }

    public UserClient verifyId(Object expectedId) {
        Assertions.assertEquals(expectedId, getId(), "User ID mismatch");
        return this;
    }

    public UserClient verifyEmail(String expectedEmail) {
        Assertions.assertEquals(expectedEmail, getEmail(), "Email mismatch");
        return this;
    }

    public UserClient verifyFirstname(String expectedFirstname) {
        Assertions.assertEquals(expectedFirstname, getFirstname(), "Firstname mismatch");
        return this;
    }

    public UserClient verifyLastname(String expectedLastname) {
        Assertions.assertEquals(expectedLastname, getLastname(), "Lastname mismatch");
        return this;
    }

    public UserClient verifyAvatar(String expectedAvatar) {
        Assertions.assertEquals(expectedAvatar, getAvatar(), "Avatar mismatch");
        return this;
    }
}
