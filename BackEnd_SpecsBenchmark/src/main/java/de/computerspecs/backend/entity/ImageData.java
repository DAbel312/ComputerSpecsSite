package de.computerspecs.backend.entity;

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

    @OneToOne(mappedBy = "image") // bidirektional
    private Article article;

    public ImageData() {}

    public ImageData(long id, String name, String type, byte[] imageData, Article article) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.imageData = imageData;
        this.article = article;
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

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}


