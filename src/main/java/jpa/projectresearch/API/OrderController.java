package jpa.projectresearch.API;

import jpa.projectresearch.Dto.OrderDto;
import jpa.projectresearch.Dto.RasaOrder;
import jpa.projectresearch.Entity.Order;
import jpa.projectresearch.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAll(){
        List<OrderDto> orderDto = orderService.GetAllOrders();
        return ResponseEntity.ok(orderDto);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<OrderDto> getById(@PathVariable Long Id){
        OrderDto orderDto = orderService.GetOrderById(Id);
        return ResponseEntity.ok(orderDto);
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto){
        OrderDto orderDto1 = orderService.CreateOrder(orderDto);
        return new ResponseEntity<>(orderDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<OrderDto> update(@PathVariable Long Id, @RequestBody OrderDto orderDto){
        OrderDto orderDto1 = orderService.UpdateOrder(Id, orderDto);
        return new ResponseEntity<>(orderDto1, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> delete(@PathVariable Long Id){
        orderService.DeleteOrder(Id);
        return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
    }

    // api chat bot
    @PostMapping("/search")
    public ResponseEntity<?> findOrder(@RequestBody RasaOrder name){
        List<OrderDto> orders = orderService.findByOrderNames(name.getOrderName());
        if(!orders.isEmpty()){
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return ResponseEntity.ok("không tìm thấy đơn hàng");
    }
}
