package de.computerspecs.backend.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue
    private long id;

    private String title;

    @Column(length = 10000)
    private String content;

    private Date date;

    private String author;

    public Article() {}

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
