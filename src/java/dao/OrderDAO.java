/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import models.Order;

/**
 *
 * @author Dell
 */
public interface OrderDAO {

    public int countHistory(String username);

    public Order getOrderById(int id);

    public List<Order> getHistory(int pageIndex, String username);
     
}
