package jpa.projectresearch.Entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jpa.projectresearch.Variable.Variable;

import java.util.*;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "status")
    private Variable.setStatus status;
    @Column(name = "order_name")
    private String orderName;

    private Date order_date;

    private Boolean checkPayment; // thanh toan qua banking set true qua cash false cho den khi da thanh toan

    private String note;

    private Variable.setStatusBanking statusBanking;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "order_item",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @JsonIgnore
    private List<Product> products;


    @ElementCollection
    @CollectionTable(name = "order_product_quantity", joinColumns = @JoinColumn(name = "order_id"))
    @MapKeyJoinColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Product, Integer> productQuantities = new HashMap<>();


    public void addProduct(Product product) {
        if(products == null) {
            products = new ArrayList<Product>();
        }
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.set(products.indexOf(product), null);
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public Order(Long orderId, Variable.setStatus status, String orderName, Date order_date, List<Product> products,User user) {
        this.orderId = orderId;
        this.status = status;
        this.orderName = orderName;
        this.order_date = order_date;
        this.products = products;
        this.user = user;
    }

    public Order(Long orderId, Variable.setStatus status, String orderName, Date order_date, Boolean checkPayment, String note, Variable.setStatusBanking statusBanking, List<Product> products, User user) {
        this.orderId = orderId;
        this.status = status;
        this.orderName = orderName;
        this.order_date = order_date;
        this.checkPayment = checkPayment;
        this.note = note;
        this.statusBanking = statusBanking;
        this.products = products;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Variable.setStatus getStatus() {
        return status;
    }

    public void setStatus(Variable.setStatus status) {
        this.status = status;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Boolean getCheckPayment() {
        return checkPayment;
    }

    public void setCheckPayment(Boolean checkPayment) {
        this.checkPayment = checkPayment;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Variable.setStatusBanking getStatusBanking() {
        return statusBanking;
    }

    public void setStatusBanking(Variable.setStatusBanking statusBanking) {
        this.statusBanking = statusBanking;
    }

    public Map<Product, Integer> getProductQuantities() {
        return productQuantities;
    }

    public void setProductQuantities(Map<Product, Integer> productQuantities) {
        this.productQuantities = productQuantities;
    }

    public Order(){

    }
}
