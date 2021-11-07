/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Cart;
import models.Feedback;
import models.Order;
import models.Users;

/**
 *
 * @author sony
 */
public class DAO {

    private Connection connection;

    public DAO(DBContext db) {
        try {
            connection = db.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
//    public void updateInfoUser(Users user) {
//        try {
//            String sql = "UPDATE [PRJ321E5_PROJECT].[dbo].[User]\n"
//                    + "   SET [password] = ?\n"
//                    + "      ,[email] = ?\n"
//                    + "      ,[phone] = ?\n"
//                    + " WHERE [username] = ?";
//
//            PreparedStatement statement = connection.prepareStatement(sql);
//            statement.setString(1, user.getPassword());
//            statement.setString(2, user.getEmail());
//            statement.setString(3, user.getPhone());
//            statement.setString(4, user.getUsername());
//            statement.executeUpdate();
//        } catch (SQLException ex) {
//            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public void addFeedback(Feedback feedback) {
        java.util.Date utilDate = new java.util.Date();
        Date now = new Date(utilDate.getTime());

        try {
            String sql = "insert into Feedback values(?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, feedback.getUsername());
            ps.setString(2, feedback.getMessage());
            ps.setDate(3, now);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }
  
    public void addToContact(String username, String email, String phone, String contents) {
        try {
            String sql = "INSERT INTO [PRJ321E5_PROJECT].[dbo].[Contact]\n"
                    + "           ([name]\n"
                    + "           ,[email]\n"
                    + "           ,[phone]\n"
                    + "           ,[contents])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, email);
            ps.setString(3, phone);
            ps.setString(4, contents);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public Feedback getFeedbackById(int id) {
        try {
            String sql = "select * from Feedback where ID = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(
                        rs.getInt("ID"),
                        rs.getString("username"),
                        rs.getString("message"),
                        rs.getDate("createAt")
                );

                return f;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public Users getUserByUsername(String username) {
        try {
            String sql = "select * from [User] where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users u = new Users();
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setGender(rs.getInt("gender"));
                u.setAddress(rs.getString("address"));
                return u;
            }
        } catch (Exception e) {
        }
        return null;
    }

    

    public int countFeedback() {
        int count = 0;
        try {
            String query = "select count(*) from Feedback";

            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return count;
    }

    public ArrayList<Feedback> getFeedbacks(int pageIndex) {
        ArrayList<Feedback> list = new ArrayList<>();
        try {
            String query = "select * from("
                    + "select ROW_NUMBER() over (order by ID desc) as rn, *\n"
                    + "from Feedback"
                    + ")as x\n"
                    + "where rn between (?-1)*?+1"
                    + "and ?*?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, pageIndex);
            ps.setInt(2, 6);
            ps.setInt(3, pageIndex);
            ps.setInt(4, 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feedback f = new Feedback(rs.getInt("ID"),
                        rs.getString("username"), rs.getString("message"),
                        rs.getDate("createAt"));
                list.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getLastOrderID() {
        int orderID = 0;
        try {
            String sql = "select top(1) OrderID from [Order] order by OrderID desc";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return orderID;
    }

    public List<Order> getAllProductByUser(String username) {
        List<Order> list = new ArrayList<>();
        try {
            String sql = "select p.ProductImage, p.ProductName, p.ProductPrice, o.OrderQuantity,o.OrderTotalPrice\n"
                    + "from [Order] o, Product p, [User] u \n"
                    + "where o.ProductID = p.ProductID and o.username = u.username and u.username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                int orderID = getLastOrderID();
                o.setOrderID(orderID + 1);
                o.setProductImage(rs.getString(1));
                o.setProductName(rs.getString(2));
                o.setProductPrice(rs.getInt(3));
                o.setQuantity(rs.getInt(4));
                o.setTotalPrice(rs.getInt(3) * rs.getInt(4));
                list.add(o);
            }
        } catch (Exception e) {
        }
        return list;
    }

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

    public void Buy(int quantity, int total, int pID, String username) {
        try {
            String sql = "insert into [Order] "
                    + "(OrderQuantity, OrderTotalPrice, ProductID, username) "
                    + "values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, quantity);
            ps.setInt(2, total);
            ps.setInt(3, pID);
            ps.setString(4, username);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    public List<Users> getAllUser() {
        List<Users> list = new ArrayList<>();
        try {
            String sql = "select username,email,phone from [dbo].[User] where username not like 'admin'";

            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users u = new Users();
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setGender(rs.getInt("gender"));
                u.setAddress(rs.getString("address"));
                list.add(u);
            }
        } catch (Exception e) {
        }
        return list;
    }

    public void deleteOrderbyusername(String username) throws SQLException {
        String sql1 = "delete from [dbo].[Order] where username = ?";
        PreparedStatement ps1 = connection.prepareStatement(sql1);
        ps1.setString(1, username);
        ps1.execute();

    }

    

    public void deleteFeedback(int id) {
        try {
            String sql = "delete from [dbo].[Feedback] where ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
        } catch (Exception e) {
        }
    }

    public void deleteByUsername(String username) {
        try {
            deleteOrderbyusername(username);
            String sql = "delete from [dbo].[User] where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.execute();
        } catch (Exception e) {
        }
    }

    
    public void deleteByorderID(String ProductID) {
        try {
            String sql = "DELETE FROM Product /n"
                    + "Where ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, ProductID);
            ps.executeQuery();
        } catch (Exception e) {

        }
    }

    public int countUser(String user_search) {
        int count = 0;
        try {
            String query = "select count(*) from [User] where username like ? and username not like ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + user_search + "%");
            ps.setString(2, "admin");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return count;
    }

    public List<Users> searchUser(int pageIndex, String user_search) {
        List<Users> list = new ArrayList<>();
        try {
            String query = "select * from("
                    + "select ROW_NUMBER() over (order by username desc) as rn, *\n"
                    + "from [User] where username like ? and username not like ?"
                    + ")as x\n"
                    + "where rn between (?-1)*?+1"
                    + "and ?*?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + user_search + "%");
            ps.setString(2, "admin");
            ps.setInt(3, pageIndex);
            ps.setInt(4, 6);
            ps.setInt(5, pageIndex);
            ps.setInt(6, 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Users u = new Users();
                u.setUsername(rs.getString("username"));
                u.setEmail(rs.getString("email"));
                u.setPhone(rs.getString("phone"));
                u.setGender(rs.getInt("gender"));
                u.setAddress(rs.getString("address"));
                list.add(u);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public int getTotalCartPrice(ArrayList<Cart> cartList) {
        int sum = 0;

        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    String sql = "select ProductPrice from Product p where p.ProductID = ? ";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setInt(1, item.getProductID());
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        sum += rs.getInt("ProductPrice") * item.getQuantity();
                    }
                }
            }

        } catch (Exception e) {
        }

        return sum;
    }

    public ArrayList<Cart> getCartProducts(ArrayList<Cart> cartList) {
        ArrayList<Cart> products = new ArrayList<>();
        try {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    String sql = "select * from Product p where p.ProductID = ? ";
                    PreparedStatement ps = connection.prepareStatement(sql);
                    ps.setInt(1, item.getProductID());
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        Cart row = new Cart();
                        row.setProductID(rs.getInt("ProductID"));
                        row.setProductName(rs.getString("ProductName"));
                        row.setProductPrice(rs.getInt("ProductPrice") * item.getQuantity());
                        row.setQuantity(item.getQuantity());
                        products.add(row);
                    }
                }
            }

        } catch (Exception e) {
        }
        return products;
    }
}
