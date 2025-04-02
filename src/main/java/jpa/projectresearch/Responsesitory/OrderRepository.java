package jpa.projectresearch.Responsesitory;
import jpa.projectresearch.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByOrderNameLike(String orderName);

    List<Order> findByOrderNameContainingIgnoreCase(String orderName);

}
