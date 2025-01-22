package jpa.projectresearch.API;

import jpa.projectresearch.Dto.ProductDto;
import jpa.projectresearch.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDto>> addProduct(ProductDto product) {
        List<ProductDto> productDto = productService.getAllProducts();
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long Id) {
        ProductDto productDto = productService.getProductById(Id);
        return ResponseEntity.ok(productDto);
    }

    @PutMapping("/{Id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long Id, @RequestBody ProductDto product) {
        ProductDto productDto = productService.updateProduct(Id, product);
        return ResponseEntity.ok(productDto);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product) {
        ProductDto productDto = productService.createProduct(product);
        return new ResponseEntity<>(productDto, HttpStatus.CREATED);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long Id) {
        productService.deleteProduct(Id);
        return new ResponseEntity<>("Product deleted Successfully !", HttpStatus.OK);
    }
}
