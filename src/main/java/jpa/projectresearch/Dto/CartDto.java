package jpa.projectresearch.Dto;

import jpa.projectresearch.Entity.User;
import lombok.*;

import java.util.List;


@Data
public class CartDto {

    private Long cartId;

    private String userName;

    private Double quantity;

    private User user;


    private List<ProductQuantityDto> productCartQuantities;


    public Long getCartId() {
        return cartId;
    }

    public void setCartId(Long cartId) {
        this.cartId = cartId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public List<ProductQuantityDto> getProductCartQuantities() {
        return productCartQuantities;
    }

    public void setProductCartQuantities(List<ProductQuantityDto> productCartQuantities) {
        this.productCartQuantities = productCartQuantities;
    }

    public CartDto(Long cartId, String userName, Double quantity, User user) {
        this.cartId = cartId;
        this.userName = userName;
        this.quantity = quantity;
        this.user = user;

    }

    public CartDto(Long cartId, String userName, Double quantity){
        this.cartId = cartId;
        this.userName = userName;
        this.quantity = quantity;
    }

    public CartDto(Long cartId, String userName, Double quantity, User user, List<ProductQuantityDto> productCartQuantities) {
        this.cartId = cartId;
        this.userName = userName;
        this.quantity = quantity;
        this.user = user;

        this.productCartQuantities = productCartQuantities;
    }

    public CartDto(){

    }
}
