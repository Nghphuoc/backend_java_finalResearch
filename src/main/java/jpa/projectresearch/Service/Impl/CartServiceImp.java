package jpa.projectresearch.Service.Impl;

import jpa.projectresearch.Dto.CartDto;
import jpa.projectresearch.Entity.Cart;
import jpa.projectresearch.Entity.Product;
import jpa.projectresearch.Mapper.CartMapper;
import jpa.projectresearch.Responsesitory.CartRepository;
import jpa.projectresearch.Responsesitory.ProductRepository;
import jpa.projectresearch.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
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
        //cartUpdate.setProducts(cart.getProducts());
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
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product productUpdate = productRepository.findById(product)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Khởi tạo productQuantities nếu chưa có
        if (cart.getProductQuantities() == null) {
            cart.setProductQuantities(new HashMap<>());
        }

        // Lấy số lượng hiện tại và cập nhật
        int currentQuantity = cart.getProductQuantities().getOrDefault(productUpdate, 0);
        cart.getProductQuantities().put(productUpdate, currentQuantity + 1);

        // Thêm vào danh sách products nếu chưa có
        if (!cart.getProducts().contains(productUpdate)) {
            cart.getProducts().add(productUpdate);
        }

        return cartRepository.save(cart);
    }


//    @Override
//    @Transactional
//    public Cart removeProduct(Long id, Long product) {
//        Cart cart = cartRepository.findById(id).orElseThrow(()-> new RuntimeException("Cart not found"));
//        Product productUpdate = productRepository.findById(product).orElseThrow(()-> new RuntimeException("Product not found"));
//        //cart.getProducts().remove(productUpdate);
//        cart.removeProduct(productUpdate);
//        return cartRepository.save(cart);
//    }

    @Override
    @Transactional
    public Cart removeProduct(Long id, Long product) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Product productUpdate = productRepository.findById(product)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Kiểm tra nếu sản phẩm có trong productQuantities
        if (cart.getProductQuantities() != null && cart.getProductQuantities().containsKey(productUpdate)) {
            int currentQuantity = cart.getProductQuantities().get(productUpdate);

            if (currentQuantity > 1) {
                // Nếu số lượng > 1, giảm số lượng
                cart.getProductQuantities().put(productUpdate, currentQuantity - 1);
            } else {
                // Nếu chỉ còn 1, xóa luôn khỏi productQuantities
                cart.getProductQuantities().remove(productUpdate);
                cart.getProducts().remove(productUpdate); // Xóa khỏi danh sách sản phẩm
            }
        } else {
            throw new RuntimeException("Product not found in cart");
        }

        return cartRepository.save(cart);
    }

    @Override
    public Cart deleteProductToCart(Long id, List<Long> productId) {
        Cart cart = cartRepository.findById(id).orElseThrow(()-> new RuntimeException("Cart not found"));
        for(Long productIdToDelete : productId) {
            Product productUpdate = productRepository.findById(productIdToDelete).orElseThrow(()-> new RuntimeException("Product not found"));
            cart.getProductQuantities().remove(productUpdate);
            cart.getProducts().remove(productUpdate);
        }
//        Product productUpdate = productRepository.findById(product).orElseThrow(()-> new RuntimeException("Product not found"));
//        //cart.getProducts().remove(productUpdate);
//        cart.removeProduct(productUpdate);
//        return cartRepository.save(cart);
        return cartRepository.save(cart);
    }

}
