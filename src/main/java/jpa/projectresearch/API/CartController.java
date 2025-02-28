package jpa.projectresearch.API;

import jpa.projectresearch.Dto.CartDto;
import jpa.projectresearch.Entity.Cart;
import jpa.projectresearch.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @GetMapping
    public ResponseEntity<List<CartDto>> getAll() {
        List<CartDto> cartDto = cartService.getAllCart();
        return ResponseEntity.ok(cartDto);
    }

    @GetMapping("/{Id}") // use this
    public ResponseEntity<CartDto> getById(@PathVariable("Id") Long Id) {
        CartDto cartDto = cartService.getCartById(Id);
        return ResponseEntity.ok(cartDto);
    }

    @PostMapping
    public ResponseEntity<CartDto> create(@RequestBody CartDto cartDto) {
        CartDto cartDtoCreated = cartService.createCart(cartDto);
        return new ResponseEntity<>(cartDtoCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<CartDto> update(@PathVariable("Id") Long Id, @RequestBody CartDto cartDto) {
        CartDto cartDtoUpdated = cartService.updateCart(Id, cartDto);
        return new ResponseEntity<>(cartDtoUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> delete(@PathVariable Long Id) {
        cartService.deleteCart(Id);
        return ResponseEntity.ok("Deleted successfully!");
    }
// add product api
    @PutMapping("/addProduct/{cartId}/{productId}")
    public ResponseEntity<?> addProduct(@PathVariable Long cartId, @PathVariable Long productId ) {
        Cart cart = cartService.addProduct(cartId, productId);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/removeProduct/{cartId}/{productId}")
    public ResponseEntity<?> removeProduct(@PathVariable Long cartId, @PathVariable Long productId ) {
        Cart cart = cartService.removeProduct(cartId, productId);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/removeProductDetail/{cartId}/{productId}")
    public ResponseEntity<?> deleteProductToCart(@PathVariable Long cartId, @PathVariable List<Long> productId ) {
        Cart cart = cartService.deleteProductToCart(cartId,productId);
        return ResponseEntity.ok(cart);
    }
}
