package de.computerspecs.backend.entity;

import java.util.List;

import jakarta.persistence.*;
import lombok.Builder;

@Entity
@Table(name = "imageData")
@Builder
public class ImageData {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String type;

    @Lob
    @Column(name = "imagedata", length = 1000)
    private byte[] imageData;

    @OneToMany(mappedBy = "image")
    private List<Article> articles;

    public ImageData() {}

    public ImageData(long id, String name, String type, byte[] imageData, List<Article> articles) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageData = imageData;
        this.articles = articles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getImageData() {
        return imageData;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticle(List<Article> articles) {
        this.articles = articles;
    }
}


