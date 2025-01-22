package jpa.projectresearch.Service;

import jpa.projectresearch.Dto.CartDto;
import org.springframework.stereotype.Component;


import java.util.List;

@Component
public interface CartService {

    List<CartDto> getAllCart();

    CartDto getCartById(Long id);

    CartDto createCart(CartDto cart);

    CartDto updateCart(Long id,CartDto cart);

    void deleteCart(Long id);
}
