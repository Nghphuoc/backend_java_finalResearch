package jpa.projectresearch.Mapper;

import jpa.projectresearch.Dto.CartDto;
import jpa.projectresearch.Dto.ProductQuantityDto;
import jpa.projectresearch.Entity.Cart;
import jpa.projectresearch.Entity.Product;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.stream.Collectors;

public class CartMapper {

    public static CartDto mapCart(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setCartId(cart.getCartId());
        cartDto.setUserName(cart.getUserName());
        cartDto.setQuantity(cart.getQuantity());
        cartDto.setUser(cart.getUser());
        //cartDto.setProducts(cart.getProducts());

        // Chuyển Map<Product, Integer> thành List<ProductQuantityDto>
        List<ProductQuantityDto> productDtos = cart.getProductQuantities()
                .entrySet()
                .stream()
                .map(entry -> {
                    Product product = entry.getKey();
                    return new ProductQuantityDto(
                            product.getProductId(),
                            entry.getValue(), // quantity
                            product.getProductName(),
                            product.getDescription(),
                            product.getPrice(),
                            product.getImageUrl(),
                            product.getStock_quantity(),
                            product.getNumber_Of_Purchases()
                    );
                })
                .collect(Collectors.toList());

        cartDto.setProductCartQuantities(productDtos);
        return cartDto;
    }

    public static Cart mapCartDto(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setCartId(cartDto.getCartId());
        cart.setUserName(cartDto.getUserName());
        cart.setQuantity(cartDto.getQuantity());
        cart.setUser(cartDto.getUser());
        //cart.setProducts(cartDto.getProducts());

        if (cartDto.getProductCartQuantities() != null) {
            Map<Product, Integer> productQuantities = new HashMap<>();
            for (ProductQuantityDto pqDto : cartDto.getProductCartQuantities()) {
                Product product = new Product();
                product.setProductId(pqDto.getProductId()); // Chỉ cần set ID, không cần toàn bộ object
                product.setProductName(pqDto.getProductName());
                product.setDescription(pqDto.getDescription());
                product.setPrice(pqDto.getPrice());
                product.setImageUrl(pqDto.getImageUrl());
                product.setStock_quantity(pqDto.getStock_quantity());
                product.setNumber_Of_Purchases(pqDto.getNumber_Of_Purchases());
                productQuantities.put(product, pqDto.getQuantity());
            }
            cart.setProductQuantities(productQuantities);
        }

        return cart;
    }
}
