package com.example.rest.common.articles;

import com.example.rest.BaseRest;
import com.example.rest.models.Article;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.junit.jupiter.api.Assertions;

@Data
@EqualsAndHashCode(callSuper = false)
public class ArticleClient extends BaseRest<Article> {

    public ArticleClient() {
        super();
    }

    public ArticleClient(Article data) {
        super(data);
    }

    public void setArticleToken(String token) {
        this.setToken(token);
    }

    public ArticleClient findById(Object id) {
        Article data = this.get("/articles/" + id, Article.class).getBody();
        this.setData(data);
        return this;
    }

    public ArticleClient[] getAll() {
        Article[] articles = this.get("/articles", Article[].class).getBody();
        if (articles == null) return new ArticleClient[0];
        ArticleClient[] clients = new ArticleClient[articles.length];
        for (int i = 0; i < articles.length; i++) {
            clients[i] = new ArticleClient(articles[i]);
        }
        return clients;
    }

    public ArticleClient create() {
        Article responseData = this.post("/articles", this.data, Article.class).getBody();
        this.data = responseData;
        return this;
    }

    public ArticleClient update() {
        Article responseData = this.put("/articles/" + this.data.getId(), this.data, Article.class).getBody();
        this.data = responseData;
        return this;
    }

    public ArticleClient patch() {
        Article responseData = this.patch("/articles/" + this.data.getId(), this.data, Article.class).getBody();
        this.data = responseData;
        return this;
    }

    public void delete(Object id) {
        this.delete("/articles/" + id);
    }

    public ArticleClient verifyId(Object expectedId) {
        Assertions.assertEquals(expectedId, data.getId(), "Article ID mismatch");
        return this;
    }

    public ArticleClient verifyUserId(Object expectedUserId) {
        Assertions.assertEquals(expectedUserId, data.getUser_id(), "User ID mismatch");
        return this;
    }

    public ArticleClient verifyTitle(String expectedTitle) {
        Assertions.assertEquals(expectedTitle, data.getTitle(), "Title mismatch");
        return this;
    }

    public ArticleClient verifyBody(String expectedBody) {
        Assertions.assertEquals(expectedBody, data.getBody(), "Body mismatch");
        return this;
    }

    public ArticleClient verifyDate(String expectedDate) {
        Assertions.assertEquals(expectedDate, data.getDate(), "Date mismatch");
        return this;
    }

    public ArticleClient verifyImage(String expectedImage) {
        Assertions.assertEquals(expectedImage, data.getImage(), "Image mismatch");
        return this;
    }
}
