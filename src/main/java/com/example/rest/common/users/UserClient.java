package com.example.rest.common.users;

import com.example.rest.BaseRest;
import com.example.rest.models.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

/**
 * Client for interacting with individual user resources.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserClient extends BaseRest<User> {

    /**
     * Default constructor.
     */
    public UserClient() {
        super();
    }

    /**
     * Retrieves a user by their ID and populates this client's data.
     * 
     * @param id The ID of the user to retrieve.
     * @return This UserClient instance.
     */
    public UserClient findById(Object id) {
        User data = this.get("/users/" + id, User.class).getBody();
        this.setData(data);
        return this;
    }

    /**
     * Creates a new user using the current data.
     * 
     * @return This UserClient instance with created user data.
     */
    public UserClient create() {
        User responseData = this.post("/users", this.data, User.class).getBody();
        this.data = responseData;
        return this;
    }

    /**
     * Updates the user using PUT (full update).
     * 
     * @return This UserClient instance with updated data.
     */
    public UserClient update() {
        User responseData = this.put("/users/" + this.data.getId(), this.data, User.class).getBody();
        this.data = responseData;
        return this;
    }

    /**
     * Updates the user using PATCH (partial update).
     * 
     * @return This UserClient instance with patched data.
     */
    public UserClient patch() {
        User responseData = this.patch("/users/" + this.data.getId(), this.data, User.class).getBody();
        this.data = responseData;
        return this;
    }

    /**
     * Deletes the user with the specified ID.
     * 
     * @param id The ID of the user to delete.
     */
    public void delete(Object id) {
        this.delete("/users/" + id);
    }

    /**
     * Verifies that the user ID matches the expected ID.
     * 
     * @param expectedId The expected user ID.
     * @return This UserClient instance.
     */
    public UserClient verifyId(Object expectedId) {
        Assertions.assertEquals(expectedId, data.getId(), "User ID mismatch");
        return this;
    }

    /**
     * Verifies that the user's email matches the expected email.
     * 
     * @param expectedEmail The expected email address.
     * @return This UserClient instance.
     */
    public UserClient verifyEmail(String expectedEmail) {
        Assertions.assertEquals(expectedEmail, data.getEmail(), "Email mismatch");
        return this;
    }

    /**
     * Verifies that the user's first name matches the expected first name.
     * 
     * @param expectedFirstname The expected first name.
     * @return This UserClient instance.
     */
    public UserClient verifyFirstname(String expectedFirstname) {
        Assertions.assertEquals(expectedFirstname, data.getFirstname(), "Firstname mismatch");
        return this;
    }

    /**
     * Verifies that the user's last name matches the expected last name.
     * 
     * @param expectedLastname The expected last name.
     * @return This UserClient instance.
     */
    public UserClient verifyLastname(String expectedLastname) {
        Assertions.assertEquals(expectedLastname, data.getLastname(), "Lastname mismatch");
        return this;
    }

    /**
     * Verifies that the user's avatar URL/path matches the expected avatar.
     * 
     * @param expectedAvatar The expected avatar URL or path.
     * @return This UserClient instance.
     */
    public UserClient verifyAvatar(String expectedAvatar) {
        Assertions.assertEquals(expectedAvatar, data.getAvatar(), "Avatar mismatch");
        return this;
    }
}
