package jpa.projectresearch.Mapper;

import jpa.projectresearch.Dto.OrderDto;
import jpa.projectresearch.Entity.Order;

public class OrderMapper {

    public static OrderDto mapOrder(Order order) {
        return new OrderDto(
                order.getOrderId(),
                order.getStatus(),
                order.getOrderName(),
                order.getOrder_date(),
                order.getCheckPayment(),
                order.getNote(),
                order.getStatusBanking(),
                order.getProducts(),
                order.getUser()

        );
    }

    public static Order mapOrderDto(OrderDto orderDto) {
        return new Order(
                orderDto.getOrderId(),
                orderDto.getStatus(),
                orderDto.getOrderName(),
                orderDto.getOrder_date(),
                orderDto.getCheckPayment(),
                orderDto.getNote(),
                orderDto.getStatusBanking(),
                orderDto.getProducts(),
                orderDto.getUser()
        );
    }
}
