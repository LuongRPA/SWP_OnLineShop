/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import java.util.List;
import models.LaptopInfo;
import models.Product;

/**
 *
 * @author Administrator
 */
public interface ProductDao {
    public List<Product> getAllProduct();
    public void UpdateProduct(Product p, int oldid);
    public void deleteByProductID(int id);
    public void deleteOrderbyproductID(int ID) throws SQLException ;
    public void addProduct(String name, int price, String image, int laptopInfoID);
    public Product getProductByID(int pid);
    public int getCount(String nameProduct) throws SQLException;
    public List<Product> getListProduct(int pageIndex, String nameProduct)throws SQLException;
    public LaptopInfo getLaptopInfo(int id);
}
