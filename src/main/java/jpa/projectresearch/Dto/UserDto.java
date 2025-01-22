package jpa.projectresearch.Dto;

import jpa.projectresearch.Entity.Cart;
import jpa.projectresearch.Entity.Order;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@NoArgsConstructor
@Data
public class UserDto {
    private Long userId;

    private String fullName;

    private String password;

    private String email;

    private String phone;

    private String address;

    private Cart cart;

    private List<Order> orders;

    public UserDto(Long userId, String fullName, String password, String email, String phone, String address, Cart cart, List<Order> orders) {
        this.userId = userId;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.cart = cart;
        this.orders = orders;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
