/*
 * Copyright(C) 2021,  FPT.
 *  LTS:
 *  LaptopShop
 *
 * Record of change:
 * DATE                       Version             AUTHOR                       DESCRIPTION
 * 2021/11/6                   1.0               HoanglV                       create file
 */
package dao;

import context.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Users;

/**
 * The class contains method find update, delete, insert user from User table in
 * database.
 *
 *
 * @author Le Viet Hoang
 */
public class UserDAO {

    private Connection connection;

    /**
     * create a connection to database. By calling getConnection from dbcontext
     *
     * @param staffName the name of a staff. It is a
     * @param db dbcontext file to connect to database change the connection
     * attribute of class
     *
     */
    public UserDAO(DBContext db) {
        try {
            connection = db.getConnection();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * This method checks if user has entered the right account information
     *
     * @param (username) Username of user, is a string type
     * @param (password) password of said username, is string type
     * @return 0 if no account, 1 if wrong password, 2 if account and password
     * is available
     */
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

    /**
     * This method is used to get data of a specific user from table User in
     * database
     *
     * @param (username) username of user, string
     * @return a user object of user.java entity
     */
    public Users getAccountByUsername(String username) {
        try {
            String sql = "SELECT * "
                    + "  FROM [PRJ321E5_PROJECT].[dbo].[User]\n"
                    + "WHERE [username] = ? ";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) { //return user if found
                return new Users(rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getString(6));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null; //user not found
    }

    /**
     * This method adds a new user into table User in database
     *
     * @param (username) new user's user name
     * @param (password) password of the account
     * @param (email) user's email address
     * @param (phone) user's phone number
     * @param (gender) 1 for male 0 for female
     * @param (address) user's address
     */
    public void signUpAccount(String username,
            String password,
            String email,
            String phone,
            int gender,
            String address) {
        try {
            String sql = "insert into [PRJ321E5_PROJECT].[dbo].[User] values(?,?,?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setInt(5, gender);
            ps.setString(6, address);
            ps.executeUpdate(); //update to db
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
                    + "      ,[gender] = ?\n"
                    + "      ,[address] = ?\n"
                    + " WHERE [username] = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, us.getUsername());
            statement.setString(2, us.getPassword());
            statement.setString(3, us.getEmail());
            statement.setString(4, us.getPhone());
            statement.setInt(5, us.getGender());
            statement.setString(6, us.getAddress());
            statement.setString(7, oldusername);
            statement.executeUpdate(); //update to db
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * this method is used to change password of a specific user
     *
     * @param (username) account needed to change password
     * @param (newPassword) new password of said account
     */
    public void changePassword(String username, String newPassword) {
        try {
            String sql = "UPDATE [PRJ321E5_PROJECT].[dbo].[User]\n"
                    + "   SET [password] = ?\n"
                    + " WHERE [username] = ?";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, newPassword);
            statement.setString(2, username);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
