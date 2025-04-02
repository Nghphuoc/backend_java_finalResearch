package jpa.projectresearch.Mapper;

import jpa.projectresearch.Dto.ProductDto;
import jpa.projectresearch.Entity.Product;

public class ProductMapper {

    public static ProductDto mapProduct(Product product) {
        return new ProductDto(
                product.getProductId(),
                product.getProductName(),
                product.getDescription(),
                product.getPrice(),
                product.getImageUrl(),
                product.getStock_quantity(),
                product.getNumber_Of_Purchases(),
                product.getCheckSale(),
                product.getCategories(),
                product.getUser()
        );
    }

    public static Product mapProductDto(ProductDto productDto) {
        return new Product(
                productDto.getProductId(),
                productDto.getProductName(),
                productDto.getDescription(),
                productDto.getPrice(),
                productDto.getImageUrl(),
                productDto.getStock_quantity(),
                productDto.getNumber_Of_Purchases(),
                productDto.getCheckSale(),
                productDto.getCategories(),
                productDto.getUser()
        );
    }
}
