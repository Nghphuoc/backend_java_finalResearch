package jpa.projectresearch.Service.Impl;

import jpa.projectresearch.Dto.OrderDto;
import jpa.projectresearch.Dto.ProductDto;
import jpa.projectresearch.Dto.RasaOrder;
import jpa.projectresearch.Entity.Order;
import jpa.projectresearch.Entity.Product;
import jpa.projectresearch.Entity.User;
import jpa.projectresearch.Mapper.OrderMapper;
import jpa.projectresearch.Mapper.ProductMapper;
import jpa.projectresearch.Responsesitory.OrderRepository;
import jpa.projectresearch.Responsesitory.ProductRepository;
import jpa.projectresearch.Responsesitory.UserRepository;
import jpa.projectresearch.Service.OrderService;
import jpa.projectresearch.Service.ProductService;
import jpa.projectresearch.Variable.Variable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public List<OrderDto> GetAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderMapper::mapOrder).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public OrderDto CreateOrder(OrderDto orderDto) {
        Order order = OrderMapper.mapOrderDto(orderDto);
        if((order.getStatusBanking() == null) ){
            order.setCheckPayment(false);
            order.setStatusBanking(Variable.setStatusBanking.Cash);
        }

        // Kiểm tra và lấy User từ cơ sở dữ liệu
        if (order.getUser() != null && order.getUser().getUserId() != null ) {
            User user = userRepository.findById(order.getUser().getUserId())
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy User với ID: " + order.getUser().getUserId()));
            // get product when user add
            order.setUser(user);
            order.setOrderName(user.getFullName());// Liên kết User vào Order
        }
        else {
            System.out.println("require much have user to create order");
        }
        if(orderDto.getProductQuantities() != null && !orderDto.getProductQuantities().isEmpty()) {
            List<Product> products = orderDto.getProductQuantities().stream()
                    .map(pqDto -> productRepository.findById(pqDto.getProductId())
                            .orElseThrow(() -> new IllegalArgumentException("Product not found with ID: " + pqDto.getProductId())))
                    .collect(Collectors.toList());
            order.setProducts(products);
        } else {
            throw new IllegalArgumentException("Order must contain at least one product.");
        }

        // Lưu Order vào cơ sở dữ liệu
        Order savedOrder = orderRepository.save(order);
        return OrderMapper.mapOrder(savedOrder);
    }

    @Override
    @Transactional
    public OrderDto UpdateOrder(Long id,OrderDto order) {
        Order orderUpdate = orderRepository.findById(id).orElseThrow(()->new RuntimeException("cannot find category"));
        orderUpdate.setOrderName(order.getOrderName());
        orderUpdate.setOrder_date(order.getOrder_date());
        orderUpdate.setStatus(order.getStatus());
       // orderUpdate.setProducts(order.getProductQuantities());
        orderUpdate.setCheckPayment(order.getCheckPayment());
        orderUpdate.setNote(order.getNote());
        orderUpdate.setCheckPayment(order.getCheckPayment());
        Order orderSave = orderRepository.save(orderUpdate);
        return OrderMapper.mapOrder(orderSave);
    }

    @Override
    @Transactional
    public void DeleteOrder(Long theId) {
        Order order = orderRepository.findById(theId).orElseThrow(()->new RuntimeException("cannot find category"));
        orderRepository.delete(order);
    }

    @Override
    @Transactional
    public OrderDto GetOrderById(Long theId) {
        Order order = orderRepository.findById(theId).orElseThrow(()->new RuntimeException("cannot find category"));
        return OrderMapper.mapOrder(order);
    }

    @Override
    public List<Order> finOrderByOrderName(String theName) {
        return orderRepository.findByOrderNameLike(theName);
    }

    @Override
    @Transactional
    public List<OrderDto> findByOrderNames(String name) {
        List<Order> orders = orderRepository.findByOrderNameContainingIgnoreCase(name);
        List<Order> finalOrders = new ArrayList<>();
        for (Order order : orders) {
            if(order.getStatus().equals(Variable.setStatus.Ordered) || order.getStatus().equals(Variable.setStatus.Shipping)){
                order.setProducts(order.getProducts());
                System.out.println((order.getProducts()));
                finalOrders.add(order);
            }
        }
        return finalOrders.stream().map(OrderMapper::mapOrder).collect(Collectors.toList());
    }
}
