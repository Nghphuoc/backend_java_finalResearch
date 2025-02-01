package jpa.projectresearch.Service.Impl;

import jpa.projectresearch.Dto.CartDto;
import jpa.projectresearch.Dto.ProductDto;
import jpa.projectresearch.Entity.Cart;
import jpa.projectresearch.Entity.Product;
import jpa.projectresearch.Mapper.CartMapper;
import jpa.projectresearch.Mapper.ProductMapper;
import jpa.projectresearch.Responsesitory.CartRepository;
import jpa.projectresearch.Responsesitory.ProductRepository;
import jpa.projectresearch.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartServiceImp implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Transactional
    public List<CartDto> getAllCart() {
        List<Cart> listCart = cartRepository.findAll();
        return listCart.stream().map(CartMapper::mapCart).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CartDto getCartById(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new RuntimeException("Cart not found"));
        return CartMapper.mapCart(cart);
    }

    @Override
    @Transactional
    public CartDto createCart(CartDto cart) {
       Cart cartSave = CartMapper.mapCartDto(cart);
       Cart cartResponse =  cartRepository.save(cartSave);
       return CartMapper.mapCart(cartResponse);
    }

    @Override
    @Transactional
    public CartDto updateCart(Long id, CartDto cart) {
        Cart cartUpdate = cartRepository.findById(id).orElseThrow(()-> new RuntimeException("Cart not found"));
        cartUpdate.setUserName(cart.getUserName());
        cartUpdate.setQuantity(cart.getQuantity());
        cartUpdate.setProducts(cart.getProducts());
        Cart cartResponse = cartRepository.save(cartUpdate);
        return CartMapper.mapCart(cartResponse);
    }

    @Override
    @Transactional
    public void deleteCart(Long id) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new RuntimeException("Cart not found"));
        cartRepository.delete(cart);
    }

    @Override
    @Transactional
    public Cart addProduct(Long id, Long product) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new RuntimeException("Cart not found"));
        Product productUpdate = productRepository.findById(product).orElseThrow(()-> new RuntimeException("Product not found"));
        cart.getProducts().add(productUpdate);
        //cart.addProduct(productUpdate);
        return cartRepository.save(cart);
    }

    @Override
    @Transactional
    public Cart removeProduct(Long id, Long product) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new RuntimeException("Cart not found"));
        Product productUpdate = productRepository.findById(product).orElseThrow(()-> new RuntimeException("Product not found"));
        //cart.getProducts().remove(productUpdate);
        cart.removeProduct(productUpdate);
        return cartRepository.save(cart);
    }
}
