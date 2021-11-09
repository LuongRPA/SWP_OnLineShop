/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * Copyright(C) 2021,  FPT.
 *  LTS:
 *  LaptopShop
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * 2021/11/10                   1.0               LongCH                       first comment
 */
package models;

import java.util.Date;

/**
 * The class is the model for all posts of the system used to transfer data between classes and layers
 *
 * @author HP
 * @author Chu Hoang Long
 */
public class Feedback {

    private int id;
    private String username;
    private String message;
    private Date createAt;

    public Feedback() {
    }

    public Feedback(int id, String username, String message, Date createAt) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.createAt = createAt;
    }

    public Feedback(String username, String message, Date createAt) {
        this.username = username;
        this.message = message;
        this.createAt = createAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

}
