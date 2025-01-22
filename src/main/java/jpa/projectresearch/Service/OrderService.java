package jpa.projectresearch.Service;

import jpa.projectresearch.Dto.OrderDto;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderService {

    List<OrderDto> GetAllOrders();

    OrderDto CreateOrder(OrderDto order);

    OrderDto UpdateOrder(Long id,OrderDto order);

    void DeleteOrder(Long theId);

    OrderDto GetOrderById(Long theId);
}
