package app.it_academy.fitnessAppProducts.service.api;

import app.it_academy.fitnessAppProducts.core.dto.pageDto.PageDto;
import app.it_academy.fitnessAppProducts.core.dto.productDto.CreateProductDto;
import app.it_academy.fitnessAppProducts.core.dto.productDto.ProductDto;
import app.it_academy.fitnessAppProducts.domain.Product;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

public interface IProductService {

    PageDto<ProductDto> getAllProducts(Integer pageNumber, Integer pageSize);

    void createProduct(CreateProductDto productDto);

    void updateProduct(UUID uuid, Instant updateLastTime, CreateProductDto productDto);

    Product findById(UUID uuid);
}
