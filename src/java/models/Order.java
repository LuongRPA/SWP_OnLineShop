/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author sony
 */
public class Order extends Product{
    private int orderID;
    private int quantity;
    private int totalPrice;
    private Users user;

    public Order(int orderID, String ProductName, int ProductPrice ,int quantity, int totalPrice) {
        super(ProductName, ProductPrice);
        this.orderID = orderID;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        
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
        return getUser().getUsername();
    }
    
    public void setUsername(String username) {
        username = getUsername();
    }

    
    
}
