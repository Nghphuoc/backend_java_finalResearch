package jpa.projectresearch.Service;

import jpa.projectresearch.Dto.OrderDto;
import jpa.projectresearch.Dto.RasaOrder;
import jpa.projectresearch.Entity.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderService {

    List<OrderDto> GetAllOrders();

    OrderDto CreateOrder(OrderDto order);

    OrderDto UpdateOrder(Long id,OrderDto order);

    void DeleteOrder(Long theId);

    OrderDto GetOrderById(Long theId);

    List<Order> finOrderByOrderName(String theName);

    List<OrderDto> findByOrderNames(String name);
}
