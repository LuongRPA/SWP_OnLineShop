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

/**
 * The class is the model for all posts of the system used to transfer data between classes and layers
 *
 * @author HP
 * @author Chu Hoang Long
 */
public class Order extends Product {

    private int orderID;
    private int quantity;
    private int totalPrice;
    private Users user;
    private String username;

    public Order(int orderID,
            String ProductName,
            int ProductPrice,
            int quantity,
            int totalPrice,
            String username) {
        super(ProductName, ProductPrice);
        this.orderID = orderID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.username = username;
    }

    @Override
    public String toString() {

        return "Order{" + "orderID=" + orderID + ", quantity=" + quantity + ", totalPrice=" + totalPrice + ", user=" + user + '}';
    }

    public Order() {
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
