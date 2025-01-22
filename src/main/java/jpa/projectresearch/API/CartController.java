package jpa.projectresearch.API;

import jpa.projectresearch.Dto.CartDto;
import jpa.projectresearch.Entity.Cart;
import jpa.projectresearch.Responsesitory.CartRepository;
import jpa.projectresearch.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
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
    public ResponseEntity<CartDto> getById(@PathVariable Long Id) {
        CartDto cartDto = cartService.getCartById(Id);
        return ResponseEntity.ok(cartDto);
    }

    @PostMapping
    public ResponseEntity<CartDto> create(@RequestBody CartDto cartDto) {
        CartDto cartDtoCreated = cartService.createCart(cartDto);
        return new ResponseEntity<>(cartDtoCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<CartDto> update(@PathVariable Long Id, @RequestBody CartDto cartDto) {
        CartDto cartDtoUpdated = cartService.updateCart(Id, cartDto);
        return new ResponseEntity<>(cartDtoUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> delete(@PathVariable Long Id) {
        cartService.deleteCart(Id);
        return ResponseEntity.ok("Deleted successfully!");
    }
}
