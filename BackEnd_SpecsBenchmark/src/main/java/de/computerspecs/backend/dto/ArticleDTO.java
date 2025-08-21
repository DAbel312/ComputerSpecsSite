package de.computerspecs.backend.dto;

import java.util.Date;

public class ArticleDTO {

    private long id;

    private String title;

    private String content;

    private Date date;

    private String author;

    public ArticleDTO(long id, String title, String content, Date date, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
        this.author = author;
    }

    public ArticleDTO() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
