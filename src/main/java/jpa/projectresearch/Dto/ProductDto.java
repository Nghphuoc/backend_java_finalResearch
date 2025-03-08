package jpa.projectresearch.Dto;

import jpa.projectresearch.Entity.Cart;
import jpa.projectresearch.Entity.Category;
import jpa.projectresearch.Entity.Order;
import jpa.projectresearch.Entity.User;
import lombok.Data;


import java.util.List;

@Data
public class ProductDto {

    private Long productId;

    private String productName;

    private String description;

    private Double price;

    private String imageUrl;

    private int stock_quantity;

    private int number_Of_Purchases;



    private List<Category> categories;



    private User user;


    public ProductDto(Long productId, String productName, String description, Double price, String imageUrl, int stock_quantity, int number_Of_Purchases, List<Category> categories, User user) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stock_quantity = stock_quantity;
        this.number_Of_Purchases = number_Of_Purchases;
        this.categories = categories;
        this.user = user;
    }

    public int getNumber_Of_Purchases() {
        return number_Of_Purchases;
    }

    public void setNumber_Of_Purchases(int number_Of_Purchases) {
        this.number_Of_Purchases = number_Of_Purchases;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getStock_quantity() {
        return stock_quantity;
    }

    public void setStock_quantity(int stock_quantity) {
        this.stock_quantity = stock_quantity;
    }


    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public ProductDto(String productName, String description, Double price, String imageUrl, int stock_quantity, int number_Of_Purchases) {
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
        this.stock_quantity = stock_quantity;
        this.number_Of_Purchases = number_Of_Purchases;
    }
    public ProductDto(){

    }
}
