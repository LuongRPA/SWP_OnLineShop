/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.Date;

/**
 *
 * @author HP
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
