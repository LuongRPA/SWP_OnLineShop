/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DaoImplements;

import context.DBContext;
import dao.OrderDAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import models.Order;

/**
 *
 * @author Dell
 */
public class OrderDaoImpl extends DBContext implements OrderDAO{
     /**
     *used to add a new post into Post table in database
     *
     * @param (count) a post object of Post.java entity
     */
    @Override
    public int countHistory(String username) {
        int count = 0;
        try {
            String query = "select count (*) from (select o.OrderID,\n"
                    + "p.ProductName,\n"
                    + "p.ProductPrice,\n"
                    + "o.OrderQuantity,\n"
                    + "o.OrderTotalPrice from Product p, [Order] o \n"
                    + "where p.ProductID = o.productid and o.username like ?) as x";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + username + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return count;

    }
    @Override
      public Order getOrderById(int id) {
        try {
            String sql = "select [Order].OrderID, Product.ProductName, Product.ProductPrice, [Order].OrderQuantity, [Order].username,[Order].OrderTotalPrice \n"
                    + "from [Order]\n"
                    + "join Product \n"
                    + "on [Order].ProductID = Product.ProductID and [Order].OrderID = ?";

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order(
                        rs.getInt("OrderID"),
                        rs.getString("ProductName"),
                        rs.getInt("ProductPrice"),
                        rs.getInt("OrderQuantity"),
                        rs.getInt("OrderTotalPrice"),
                        rs.getString("username"));
                return o;
            }
        } catch (Exception e) {
        }
        return null;
    }
    @Override
      public List<Order> getHistory(int pageIndex, String username) {

        List<Order> list = new ArrayList();
        try {
            String sql = "select * from(select ROW_NUMBER() over (order by history.OrderID desc) as rn, * \n"
                    + "from (select o.OrderID,\n"
                    + "p.ProductName,\n"
                    + "p.ProductPrice,\n"
                    + "o.OrderQuantity,\n"
                    + "o.username,\n"
                    + "o.OrderTotalPrice from Product p, [Order] o \n"
                    + "where p.ProductID = o.productid and o.username like ?) as history )as x "
                    + "where rn between (?-1)*?+1"
                    + "and ?*?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + username + "%");
            ps.setInt(2, pageIndex);
            ps.setInt(3, 6);
            ps.setInt(4, pageIndex);
            ps.setInt(5, 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order(
                        rs.getInt("OrderID"),
                        rs.getString("ProductName"),
                        rs.getInt("ProductPrice"),
                        rs.getInt("OrderQuantity"),
                        rs.getInt("OrderTotalPrice"),
                        rs.getString("username"));
                list.add(o);
            }
        } catch (Exception e) {
        }
        return list;
    }
}
