package jpa.projectresearch.API;

import com.fasterxml.jackson.databind.ObjectMapper;
import jpa.projectresearch.Dto.ProductDto;
import jpa.projectresearch.Dto.RasaProduct;
import jpa.projectresearch.Entity.Product;
import jpa.projectresearch.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
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
        return ResponseEntity.ok(productDto);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long Id) {
        productService.deleteProduct(Id);
        return new ResponseEntity<>("Product deleted Successfully !", HttpStatus.OK);
    }

    // api chat bot
    @PostMapping("/search")
    public ResponseEntity<?> findProduct(@RequestBody RasaProduct name) {
        List<Product> product = productService.findByProductNameContainingIgnoreCase(name.getProduct());
        if(!product.isEmpty()) {
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return ResponseEntity.ok("Không tìm thấy sản phẩm");
    }
}
