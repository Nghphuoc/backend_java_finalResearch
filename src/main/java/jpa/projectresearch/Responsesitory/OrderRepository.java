package jpa.projectresearch.Responsesitory;

import jpa.projectresearch.Entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
