package jpa.projectresearch.Mapper;

import jpa.projectresearch.Dto.OrderDto;
import jpa.projectresearch.Dto.OrderDto.ProductQuantityDto;
import jpa.projectresearch.Entity.Order;
import jpa.projectresearch.Entity.Product;

import java.util.*;
import java.util.stream.Collectors;

public class OrderMapper {

    public static OrderDto mapOrder(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setStatus(order.getStatus());
        orderDto.setOrderName(order.getOrderName());
        orderDto.setOrder_date(order.getOrder_date());
        orderDto.setCheckPayment(order.getCheckPayment());
        orderDto.setNote(order.getNote());
        orderDto.setStatusBanking(order.getStatusBanking());
        orderDto.setUser(order.getUser());

        // Chuyển đổi từ Map<Product, Integer> sang List<ProductQuantityDto>
        List<ProductQuantityDto> productDtos = order.getProductQuantities()
                .entrySet()
                .stream()
                .map(entry -> new ProductQuantityDto(entry.getKey().getProductId(), entry.getValue()))
                .collect(Collectors.toList());

        orderDto.setProductQuantities(productDtos);

        return orderDto;
    }

    public static Order mapOrderDto(OrderDto orderDto) {
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setStatus(orderDto.getStatus());
        order.setOrderName(orderDto.getOrderName());
        order.setOrder_date(orderDto.getOrder_date());
        order.setCheckPayment(orderDto.getCheckPayment());
        order.setNote(orderDto.getNote());
        order.setStatusBanking(orderDto.getStatusBanking());
        order.setUser(orderDto.getUser());

        if (orderDto.getProductQuantities() != null) {
            Map<Product, Integer> productQuantities = new HashMap<>();
            for (OrderDto.ProductQuantityDto pqDto : orderDto.getProductQuantities()) {
                Product product = new Product();
                product.setProductId(pqDto.getProductId()); // Chỉ cần set ID, không cần toàn bộ object
                productQuantities.put(product, pqDto.getQuantity());
            }
            order.setProductQuantities(productQuantities);
        }

        return order;
    }
}
