package jpa.projectresearch.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;


    @Column(nullable = false)
    private String fullName;


    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    @Email
    private String email;


    @Column(nullable = false)
    private String phone;


    @Column(nullable = false)
    private String address;


    @OneToOne(fetch = FetchType.EAGER,cascade = {CascadeType.ALL},orphanRemoval = true)
    @JoinColumn(name = "cart_Id" , referencedColumnName = "cartId")
    @JsonIgnore
    private Cart cart;


    @OneToMany(fetch = FetchType.EAGER,mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonIgnore
    private List<Order> orders;

    public User(Long userId, String fullName, String password, String email, String phone, String address, Cart cart, List<Order> orders) {
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

    public @Email String getEmail() {
        return email;
    }

    public void setEmail(@Email String email) {
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

    public User(){
    }
}

