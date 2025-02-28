package jpa.projectresearch.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cartId;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "quantity")
    private Double quantity;

    @OneToOne(mappedBy = "cart",fetch = FetchType.EAGER,cascade = {CascadeType.ALL})
    @JsonIgnore
    private User user;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinTable(
    name = "cart_item",
        joinColumns =@JoinColumn(name = "cart_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnore

    private List<Product> products;

    @ElementCollection
    @CollectionTable(name = "cart_product_quantity", joinColumns = @JoinColumn(name = "cart_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")

    private Map<Product, Integer> productQuantities = new HashMap<>();

    public void addProduct(Product product) { // use for personal when customer add product on cart
        if(products == null) {
            products = new ArrayList<>();
        }
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.set(products.indexOf(product), null);
    }

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

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Cart(Long cartId, String userName, Double quantity, User user, List<Product> products) {
        this.cartId = cartId;
        this.userName = userName;
        this.quantity = quantity;
        this.user = user;
        this.products = products;
    }
    public Cart()
    {

    }

    public Map<Product, Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(Map<Product, Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }

    public Cart(String userName, Double quantity) {

        this.userName = userName;
        this.quantity = quantity;
    }
}
