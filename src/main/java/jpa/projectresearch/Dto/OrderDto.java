package jpa.projectresearch.Dto;

import jpa.projectresearch.Entity.Product;
import jpa.projectresearch.Entity.User;
import jpa.projectresearch.Variable.Variable;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDto {

    private Long orderId;

    private Variable.setStatus status;

    private String orderName;

    private Date order_date;

    private Boolean checkPayment;

    private String note;

    private Variable.setStatusBanking statusBanking;

    private User user;

    private List<Product> products;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public OrderDto(Long orderId, Variable.setStatus status, String orderName, Date order_date, List<Product> products, User user) {
        this.orderId = orderId;
        this.status = status;
        this.orderName = orderName;
        this.order_date = order_date;
        this.products = products;
        this.user = user;
    }

    public OrderDto(Long orderId, Variable.setStatus status, String orderName, Date order_date, Boolean checkPayment, String note, Variable.setStatusBanking statusBanking, List<Product> products, User user) {
        this.orderId = orderId;
        this.status = status;
        this.orderName = orderName;
        this.order_date = order_date;
        this.checkPayment = checkPayment;
        this.note = note;
        this.statusBanking = statusBanking;
        this.user = user;
        this.products = products;
    }

    public OrderDto(){

    }
}
