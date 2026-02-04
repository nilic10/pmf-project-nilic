package com.example.rest.common.users;

import com.example.rest.BaseRest;
import com.example.rest.models.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

@Data
@EqualsAndHashCode(callSuper = false)
public class UserClient extends BaseRest<User> {

    public UserClient() {
        super();
    }

    public UserClient(User data) {
        super(data);
    }

    public void setUserToken(String token) {
        this.setToken(token);
    }

    public UserClient findById(Object id) {
        User data = this.get("/users/" + id, User.class).getBody();
        this.setData(data);
        return this;
    }

    public UserClient[] getAll() {
        User[] users = this.get("/users", User[].class).getBody();
        if (users == null) return new UserClient[0];
        UserClient[] clients = new UserClient[users.length];
        for (int i = 0; i < users.length; i++) {
            clients[i] = new UserClient(users[i]);
        }
        return clients;
    }

    public UserClient create() {
        User responseData = this.post("/users", this.data, User.class).getBody();
        this.data = responseData;
        return this;
    }

    public UserClient update() {
        User responseData = this.put("/users/" + this.data.getId(), this.data, User.class).getBody();
        this.data = responseData;
        return this;
    }

    public UserClient patch() {
        User responseData = this.patch("/users/" + this.data.getId(), this.data, User.class).getBody();
        this.data = responseData;
        return this;
    }

    public void delete(Object id) {
        this.delete("/users/" + id);
    }

    public UserClient verifyId(Object expectedId) {
        Assertions.assertEquals(expectedId, data.getId(), "User ID mismatch");
        return this;
    }

    public UserClient verifyEmail(String expectedEmail) {
        Assertions.assertEquals(expectedEmail, data.getEmail(), "Email mismatch");
        return this;
    }

    public UserClient verifyFirstname(String expectedFirstname) {
        Assertions.assertEquals(expectedFirstname, data.getFirstname(), "Firstname mismatch");
        return this;
    }

    public UserClient verifyLastname(String expectedLastname) {
        Assertions.assertEquals(expectedLastname, data.getLastname(), "Lastname mismatch");
        return this;
    }

    public UserClient verifyAvatar(String expectedAvatar) {
        Assertions.assertEquals(expectedAvatar, data.getAvatar(), "Avatar mismatch");
        return this;
    }
}
