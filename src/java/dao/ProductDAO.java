/*
 * Copyright(C) 2021,  FPT.
 *  LTS:
 *  LaptopShop
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * 2021/11/6                   1.0               HoanglV                        first comment
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.LaptopInfo;
import models.Product;

/**
 * The class contains method find update, delete, insert product from product
 * and productinfo table indatabase.
 *
 *
 * @author Le Viet Hoang
 */
public class ProductDAO {

    private Connection connection;

    /**
     * create a connection to database. By calling getConnection from dbcontext
     *
     * @param db dbcontext file to connect to database change the connection
     * attribute of class
     *
     */
    public ProductDAO(DBContext db) {
        try {
            connection = db.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method add a new product in to product table in database
     *
     * @param (id) Username of user, is a string type
     * @param (name) password of said username, is string type
     * @param (price) password of said username, is string type
     * @param (image) password of said username, is string type
     * @return 0 if no account, 1 if wrong password, 2 if account and password
     * is available
     */
    

    public List<Product> getAllProduct() {
        List<Product> list = new ArrayList<>();
        try {
            String sql = "select * from Product";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product(rs.getInt("ProductID"), rs.getString("ProductName"),
                        rs.getInt("ProductPrice"), rs.getString("ProductImage"), rs.getDate("CreatedDate"),
                        rs.getDate("UpdatedDate"), rs.getInt("LaptopInfoID"));
                list.add(p);
            }

        } catch (Exception e) {
        }
        return list;
    }

    public void UpdateProduct(Product p, int oldid) {
        try {
            String sql = "UPDATE [dbo].[Product]\n"
                    + "   SET [ProductName] = ?\n"
                    + "      ,[ProductPrice] = ?\n"
                    + "      ,[ProductImage] = ?\n"
                    + "      ,[LaptopInfoID] = ?\n"
                    + " WHERE [ProductID] = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, p.getProductName());
            statement.setInt(2, p.getProductPrice());
            statement.setString(3, p.getProductImage());
            statement.setInt(4, p.getLaptopInfoID());
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

    public void deleteOrderbyproductID(int ID) throws SQLException {
        String sql1 = "delete from [dbo].[Order] where ProductID = ?";
        PreparedStatement ps1 = connection.prepareStatement(sql1);
        ps1.setInt(1, ID);
        ps1.execute();
    }


    

    public void addProduct(String name, int price, String image, int laptopInfoID) {
        java.util.Date utilDate = new java.util.Date();
        Date now = new Date(utilDate.getTime());

        String sql = "insert into product values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, price);
            ps.setString(3, image);
            ps.setDate(4, now);
            ps.setDate(5, now);
            ps.setInt(6, laptopInfoID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
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
                p.setLaptopInfoID(rs.getInt("LaptopInfoID"));
                return p;
            }
        } catch (Exception e) {
        }
        return null;
    }
    
        public int getCount(String nameProduct) throws SQLException {
        int count = 0;
        try {
            String query = "select count(*) from Product where ProductName like ?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + nameProduct + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return count;

    }
    public List<Product> getListProduct(int pageIndex, String nameProduct) throws SQLException {
        List<Product> list = new ArrayList<>();
        try {
            String query = "select * from("
                    + "select ROW_NUMBER() over (order by UpdatedDate desc) as rn, *\n"
                    + "from Product where ProductName like ?"
                    + ")as x\n"
                    + "where rn between (?-1)*?+1"
                    + "and ?*?";

            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, "%" + nameProduct + "%");
            ps.setInt(2, pageIndex);
            ps.setInt(3, 6);
            ps.setInt(4, pageIndex);
            ps.setInt(5, 6);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductID(rs.getInt("ProductID"));
                p.setProductName(rs.getString("ProductName"));
                p.setProductPrice(rs.getInt("ProductPrice"));
                p.setProductImage(rs.getString("ProductImage"));
                p.setCreatedDate(rs.getDate("CreatedDate"));
                p.setUpdatedDate(rs.getDate("UpdatedDate"));
                list.add(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    public LaptopInfo getLaptopInfo(int id) {
        try {
            String sql = "select * from LaptopInfo l where l.ID = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                LaptopInfo laptopInfo = new LaptopInfo();
                laptopInfo.setId(rs.getInt("ID"));
                laptopInfo.setCpu(rs.getString("CPU"));
                laptopInfo.setRam(rs.getString("RAM"));
                laptopInfo.setScreen(rs.getString("Screen"));
                laptopInfo.setGraphic(rs.getString("Graphic"));
                laptopInfo.setHardDrive(rs.getString("HardDrive"));
                laptopInfo.setWeigh(rs.getString("Weigh"));
                laptopInfo.setOrigin(rs.getString("Origin"));
                laptopInfo.setDebutYear(rs.getInt("DebutYear"));
                return laptopInfo;
            }
        } catch (Exception e) {
        }
        return null;
    }
}

