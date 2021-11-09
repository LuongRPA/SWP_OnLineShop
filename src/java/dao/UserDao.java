/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import models.Users;

/**
 *
 * @author Administrator
 */
public interface UserDao {

    public int checkUserAccount(String username, String password);

    public Users getAccountByUsername(String username);

    public void signUpAccount(String username,String password, String email, String phone, int gender, String address);
    
    public void UpdateMethod(Users us, String oldusername);
    
    public void changePassword(String username, String newPassword);
}
