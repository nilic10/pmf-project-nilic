package com.example.rest.common.articles;

import com.example.rest.BaseRest;
import com.example.rest.models.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

/**
 * Client for interacting with individual article resources.
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleClient extends BaseRest<Article> {

    /**
     * Default constructor.
     */
    public ArticleClient() {
        super();
    }

    /**
     * Constructor with initial article data.
     * 
     * @param data Initial article data.
     */
    public ArticleClient(Article data) {
        super(data);
    }


    /**
     * Retrieves an article by its ID and populates this client's data.
     * 
     * @param id The ID of the article to retrieve.
     * @return This ArticleClient instance.
     */
    public ArticleClient findById(Object id) {
        Article data = this.get("/articles/" + id, Article.class).getBody();
        this.setData(data);
        return this;
    }

    /**
     * Creates a new article using the provided token and data.
     *
     * @param token The bearer token for authorization.
     * @param data  The article data to create.
     * @return This ArticleClient instance with created article data.
     */
    public ArticleClient create(String token, Article data) {
        this.setToken(token);
        Article responseData = this.post("/articles", data, Article.class).getBody();
        this.setData(responseData);
        return this;
    }

    /**
     * Creates a new article using the current data.
     *
     * @return This ArticleClient instance with created article data.
     */
    public ArticleClient create() {
        Article responseData = this.post("/articles", this.data, Article.class).getBody();
        this.setData(responseData);
        return this;
    }

    /**
     * Updates the article using PUT (full update).
     *
     * @param token The bearer token for authorization.
     * @param data  The article data to update.
     * @return This ArticleClient instance with updated data.
     */
    public ArticleClient update(String token, Article data) {
        this.setToken(token);
        Article responseData = this.put("/articles/" + data.getId(), data, Article.class).getBody();
        this.setData(responseData);
        return this;
    }

    /**
     * Updates the article using PATCH (partial update).
     *
     * @param token The bearer token for authorization.
     * @param data  The article data to patch.
     * @return This ArticleClient instance with patched data.
     */
    public ArticleClient patch(String token, Article data) {
        this.setToken(token);
        Article responseData = this.patch("/articles/" + data.getId(), data, Article.class).getBody();
        this.setData(responseData);
        return this;
    }

    /**
     * Deletes the article with the specified ID.
     * 
     * @param id The ID of the article to delete.
     */
    public void delete(Object id) {
        this.delete("/articles/" + id);
    }

    /**
     * Verifies that the article ID matches the expected ID.
     * 
     * @param expectedId The expected article ID.
     * @return This ArticleClient instance.
     */
    public ArticleClient verifyId(Object expectedId) {
        Assertions.assertEquals(expectedId, data.getId(), "Article ID mismatch");
        return this;
    }

    /**
     * Verifies that the article's user ID matches the expected user ID.
     * 
     * @param expectedUserId The expected user ID.
     * @return This ArticleClient instance.
     */
    public ArticleClient verifyUserId(Object expectedUserId) {
        Assertions.assertEquals(expectedUserId, data.getUser_id(), "User ID mismatch");
        return this;
    }

    /**
     * Verifies that the article title matches the expected title.
     * 
     * @param expectedTitle The expected title.
     * @return This ArticleClient instance.
     */
    public ArticleClient verifyTitle(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, data.getTitle(), "Title mismatch");
        return this;
    }

    /**
     * Verifies that the article body matches the expected body.
     * 
     * @param expectedBody The expected body content.
     * @return This ArticleClient instance.
     */
    public ArticleClient verifyBody(String expectedBody) {
        Assertions.assertEquals(expectedBody, data.getBody(), "Body mismatch");
        return this;
    }

    /**
     * Verifies that the article date matches the expected date.
     * 
     * @param expectedDate The expected date string.
     * @return This ArticleClient instance.
     */
    public ArticleClient verifyDate(String expectedDate) {
        Assertions.assertEquals(expectedDate, data.getDate(), "Date mismatch");
        return this;
    }

    /**
     * Verifies that the article image URL/path matches the expected image.
     * 
     * @param expectedImage The expected image URL or path.
     * @return This ArticleClient instance.
     */
    public ArticleClient verifyImage(String expectedImage) {
        Assertions.assertEquals(expectedImage, data.getImage(), "Image mismatch");
        return this;
    }
}
