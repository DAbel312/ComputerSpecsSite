package de.computerspecs.backend.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    @Column(length = 10000)
    private String content;

    private Date date;

    private String author;

    @ManyToOne(fetch = FetchType.LAZY, optional = true) 
    @JoinColumn(name = "image_id")
    private ImageData image;   

    public Article() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public ImageData getImageData() {
        return image;
    }

    public void setImageData(ImageData imageData) {
        this.image = imageData;
    }
}
