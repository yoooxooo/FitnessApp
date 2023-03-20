package app.it_academy.fitnessAppProducts.web.controllers;

import app.it_academy.fitnessAppProducts.core.dto.pageDto.PageDto;
import app.it_academy.fitnessAppProducts.core.dto.productDto.CreateProductDto;
import app.it_academy.fitnessAppProducts.core.dto.productDto.ProductDto;
import app.it_academy.fitnessAppProducts.service.RecipeService;
import app.it_academy.fitnessAppProducts.service.api.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final IProductService service;

    public ProductController(IProductService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<PageDto<ProductDto>> getAllProducts(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                            @RequestParam(value = "size", required = false, defaultValue = "20") Integer size) {
        return ResponseEntity.ok().body(service.getAllProducts(page, size));
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductDto productDto) {
        service.createProduct(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping (path = "/{uuid}/dt_update/{dt_update}")
    public ResponseEntity<String> updateProduct(
            @PathVariable("uuid") UUID uuid,
            @PathVariable ("dt_update") Instant localDateTime,
            @RequestBody CreateProductDto productDto
    ) {
        service.updateProduct(uuid, localDateTime, productDto);
        return ResponseEntity.ok().build();
    }

}
