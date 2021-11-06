/*
 * Copyright(C) 2021,  FPT.
 *  LTS:
 *  LaptopShop
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * 2021/11/6                   1.0               HoanglV                        first comment
 */
package models;

import java.util.Date;

/**
 * The class is the model for all posts of the system used to transfer data between classes and layers
 *
 * @author Le Viet Hoang
 */
public class Post {
    private int id;
    private String title;
    private String image;
    private String content;
    private Date publicAt;

    public Post() {
    }

    public Post(int id, String title, String image, String content, Date publicAt) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.content = content;
        this.publicAt = publicAt;
    }

    public Post(String title, String image, String content, Date publicAt) {
        this.title = title;
        this.image = image;
        this.content = content;
        this.publicAt = publicAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublicAt() {
        return publicAt;
    }

    public void setPublicAt(Date publicAt) {
        this.publicAt = publicAt;
    }
    
    
}
