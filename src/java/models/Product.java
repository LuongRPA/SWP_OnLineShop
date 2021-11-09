
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
public class Product {

    private int ProductID;
    private String ProductName;
    private int ProductPrice;
    private String ProductImage;
    private Date createdDate;
    private Date updatedDate;
    private int laptopInfoID;

    public Product(String ProductName, int ProductPrice) {

        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
    }

    public Product(
            int ProductID,
            String ProductName,
            int ProductPrice,
            String ProductImage,
            Date createdDate,
            Date updatedDate,
            int laptopInfoID) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.ProductImage = ProductImage;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.laptopInfoID = laptopInfoID;
    }

    public Product() {
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int ProductPrice) {
        this.ProductPrice = ProductPrice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String ProductImage) {
        this.ProductImage = ProductImage;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getLaptopInfoID() {
        return laptopInfoID;
    }

    public void setLaptopInfoID(int laptopInfoID) {
        this.laptopInfoID = laptopInfoID;
    }

}
