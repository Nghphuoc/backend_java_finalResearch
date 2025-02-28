package jpa.projectresearch.Dto;

import jpa.projectresearch.Entity.Product;
import jpa.projectresearch.Entity.User;
import jpa.projectresearch.Variable.Variable;
import java.util.Date;
import java.util.List;

public class OrderDto {

    private Long orderId;
    private Variable.setStatus status;
    private String orderName;
    private Date order_date;
    private Boolean checkPayment;
    private String note;
    private Variable.setStatusBanking statusBanking;
    private User user;
    private Double totalPrice;
    // Chỉ giữ danh sách ProductQuantityDto thay vì List<Product>
    private List<ProductQuantityDto> productQuantities;




    // Getters & Setters
    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }

    public Variable.setStatus getStatus() { return status; }
    public void setStatus(Variable.setStatus status) { this.status = status; }

    public String getOrderName() { return orderName; }
    public void setOrderName(String orderName) { this.orderName = orderName; }

    public Date getOrder_date() { return order_date; }
    public void setOrder_date(Date order_date) { this.order_date = order_date; }

    public Boolean getCheckPayment() { return checkPayment; }
    public void setCheckPayment(Boolean checkPayment) { this.checkPayment = checkPayment; }

    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }

    public Variable.setStatusBanking getStatusBanking() { return statusBanking; }
    public void setStatusBanking(Variable.setStatusBanking statusBanking) { this.statusBanking = statusBanking; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<ProductQuantityDto> getProductQuantities() { return productQuantities; }
    public void setProductQuantities(List<ProductQuantityDto> productQuantities) { this.productQuantities = productQuantities; }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderDto(Long orderId, Variable.setStatus status, String orderName, Date order_date, Boolean checkPayment, String note, Variable.setStatusBanking statusBanking, User user, Double totalPrice, List<ProductQuantityDto> productQuantities) {
        this.orderId = orderId;
        this.status = status;
        this.orderName = orderName;
        this.order_date = order_date;
        this.checkPayment = checkPayment;
        this.note = note;
        this.statusBanking = statusBanking;
        this.user = user;
        this.totalPrice = totalPrice;
        this.productQuantities = productQuantities;
    }

    // Constructor
    public OrderDto(Long orderId, Variable.setStatus status, String orderName, Date order_date, Boolean checkPayment, String note, Variable.setStatusBanking statusBanking, List<ProductQuantityDto> productQuantities, User user) {
        this.orderId = orderId;
        this.status = status;
        this.orderName = orderName;
        this.order_date = order_date;
        this.checkPayment = checkPayment;
        this.note = note;
        this.statusBanking = statusBanking;
        this.user = user;
        this.productQuantities = productQuantities;
    }

    public OrderDto() { }
}
