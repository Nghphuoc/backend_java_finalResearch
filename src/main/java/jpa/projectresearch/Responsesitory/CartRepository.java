package jpa.projectresearch.Responsesitory;

import jpa.projectresearch.Entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
