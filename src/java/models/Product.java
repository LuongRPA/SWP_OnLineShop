
package models;


public class Product {
    private int ProductID;
    private String ProductName;
    private int ProductPrice;
    private String ProductImage;

    public Product(String ProductName, int ProductPrice) {
        
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
       
    }

    public Product(int ProductID, String ProductName, int ProductPrice, String ProductImage) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.ProductPrice = ProductPrice;
        this.ProductImage = ProductImage;
    }

    public Product() {
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getProductPrice() {
        return ProductPrice;
    }

    public void setProductPrice(int ProductPrice) {
        this.ProductPrice = ProductPrice;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String ProductImage) {
        this.ProductImage = ProductImage;
    }
    
    
}
