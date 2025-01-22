package jpa.projectresearch.Mapper;

import jpa.projectresearch.Dto.CartDto;
import jpa.projectresearch.Entity.Cart;

public class CartMapper {

    public static CartDto mapCart(Cart cart) {
        return new CartDto(
                cart.getCartId(),
                cart.getUserName(),
                cart.getQuantity(),
                cart.getUser(),
                cart.getProducts()
        );
    }

    public static Cart mapCartDto(CartDto cartDto) {
        return new Cart(
                cartDto.getCartId(),
                cartDto.getUserName(),
                cartDto.getQuantity(),
                cartDto.getUser(),
                cartDto.getProducts()
        );
    }
}
