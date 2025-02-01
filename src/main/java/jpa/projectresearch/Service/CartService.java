package jpa.projectresearch.Service;
import jpa.projectresearch.Dto.CartDto;
import jpa.projectresearch.Entity.Cart;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface CartService {

    List<CartDto> getAllCart();

    CartDto getCartById(Long id);

    CartDto createCart(CartDto cart);

    CartDto updateCart(Long id,CartDto cart);

    void deleteCart(Long id);

    Cart addProduct(Long id, Long product);

    Cart removeProduct(Long id, Long product);
}
