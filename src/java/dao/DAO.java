/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.Order;
import models.Product;
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

    public Users getAccountByUsername(String username) {
        try {
            String sql = "SELECT * "
                    + "  FROM [PRJ321E5_PROJECT].[dbo].[User]\n"
                    + "WHERE [username] = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Users(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public int checkUserAccount(String username, String password) {
        Users acc = getAccountByUsername(username);
        if (acc == null) {
            return 0; //Account is not available
        } else if (!acc.getPassword().equals(password)) {
            return 1; //Wrong password
        } else {
            return 2; //Account available
        }
    }

    public void signUpAccount(String username, String password, String email, String phone) {
        try {
            String sql = "INSERT INTO [PRJ321E5_PROJECT].[dbo].[User]\n"
                    + "           ([username]\n"
                    + "           ,[password]\n"
                    + "           ,[email]\n"
                    + "           ,[phone])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public void UpdateMethod(Users us, String oldusername) {
        try {
            String sql = "UPDATE [PRJ321E5_PROJECT].[dbo].[User]\n"
                    + "   SET [username] = ?\n"
                    + "      ,[password] = ?\n"
                    + "      ,[email] = ?\n"
                    + "      ,[phone] = ?\n"
                    + " WHERE [username] = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, us.getUsername());
            statement.setString(2, us.getPassword());
            statement.setString(3, us.getEmail());
            statement.setString(4, us.getPhone());
            statement.setString(5, oldusername);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
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

    public void addtoProduct(int id, String name, int price, String image) throws SQLException {
        String sql = "INSERT INTO [PRJ321E5_PROJECT].[dbo].[Product]\n"
                + "           ([id]\n"
                + "           ,[name]\n"
                + "           ,[price]\n"
                + "           ,[image])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql);
        JOptionPane.showMessageDialog(null, id + " " + name + " " + price + " " + image);
        ps.setInt(1, id);
        ps.setString(2, name);
        ps.setInt(3, price);
        ps.setString(4, image);
        ps.executeUpdate();
    }

    public Product getProductByID(int pid) {
        try {
            String sql = "select * from Product p where p.ProductID = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, pid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setProductPrice(rs.getInt("ProductPrice"));
                p.setProductImage(rs.getString("ProductImage"));
                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }

    public List<Product> getListProduct(int pageIndex) throws SQLException {
        List<Product> list = new ArrayList<>();
        try {
            String query = "select * from("
                    + "select ROW_NUMBER() over (order by ProductID ASC) as rn, *\n"
                    + "from Product"
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
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setProductPrice(rs.getInt("ProductPrice"));
                p.setProductImage(rs.getString("ProductImage"));
                list.add(p);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Product";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductID"), rs.getString("ProductName"),
                        rs.getInt("ProductPrice"), rs.getString("ProductImage"));
                list.add(p);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public void UpdateProduct(Product p, int oldid) {
        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "   SET [ProductID] = ?\n"
                    + "      ,[ProductName] = ?\n"
                    + "      ,[ProductPrice] = ?\n"
                    + "      ,[ProductImage] = ?\n"
                    + " WHERE [ProductID] = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, p.getProductID());
            statement.setString(2, p.getProductName());
            statement.setInt(3, p.getProductPrice());
            statement.setString(4, p.getProductImage());
            statement.setInt(5, oldid);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteByProductID(int id) {
        try {
            deleteOrderbyproductID(id);

            String sql = "DELETE FROM [dbo].[Product] WHERE ProductID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
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

    public List<Order> getHistory(String username) {
        List<Order> list = new ArrayList();
        try {
            String sql = "select o.OrderID, p.ProductName, p.ProductPrice,o.OrderQuantity, o.OrderTotalPrice\n"
                    + "  from Product p, [Order] o\n"
                    + "  where p.ProductID = o.productid and o.username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Order(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getInt(5)));

            }
        } catch (Exception e) {
        }
        return list;
    }

    public void Buy(int orderID, int quantity, int total, int pID, String username) {
        try {
            String sql = "insert into [Order] "
                    + "(OrderID, OrderQuantity, OrderTotalPrice, ProductID, username) "
                    + "values (?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderID);
            ps.setInt(2, quantity);
            ps.setInt(3, total);
            ps.setInt(4, pID);
            ps.setString(5, username);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public int getCount() throws SQLException {
        int count = 0;
        try {
            String query = "select count(*) from Product";

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

    public void deleteOrderbyproductID(int ID) throws SQLException {
        String sql1 = "delete from [dbo].[Order] where ProductID = ?";
        PreparedStatement ps1 = connection.prepareStatement(sql1);
        ps1.setInt(1, 1);
        ps1.execute();

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

    public int checkProduct(int id) {
        Product acc = getProductByID(id);
        if (acc == null) {
            return 0; //Product is not available
        } else {
            return 1; //Wrong password
        }
    }

    public void editProduct(int productID, String Productname, int ProductPrice, String Productimgae) {
        try {

            String sql = "UPDATE Product\n"
                    + "   SET [ProductName] = ?\n"
                    + "      ,[ProductPrice] = ?\n"
                    + "      ,[ProductImage] = ?\n"
                    + " WHERE ProductID=?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, Productname);
            ps.setInt(2, ProductPrice);
            ps.setString(3, Productimgae);
            ps.setInt(4, productID);
            ps.execute();
        } catch (Exception e) {
        }
    }

    public void addProduct(int id, String name, int price, String image) {
        String sql = "INSERT INTO [dbo].[Product]\n"
                + "           ([ProductID]\n"
                + "           ,[ProductName]\n"
                + "           ,[ProductPrice]\n"
                + "           ,[ProductImage])\n"
                + "     VALUES\n"
                + "           (?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setInt(3, price);
            ps.setString(4, image);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
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
}
