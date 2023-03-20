package app.it_academy.fitnessAppProducts.mappers;

import app.it_academy.fitnessAppProducts.core.dto.productDto.CreateProductDto;
import app.it_academy.fitnessAppProducts.core.dto.productDto.ProductDto;
import app.it_academy.fitnessAppProducts.domain.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper
public interface ProductMapper {

    @Mapping(target = "creationDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "updateDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID())")
    Product createEntity(CreateProductDto dto);


    ProductDto createDto(Product product);

    @Mapping(target = "creationDate", source = "oldProduct.creationDate")
    @Mapping(target = "updateDate", source = "oldProduct.updateDate")
    @Mapping(target = "id", source = "oldProduct.id")
    @Mapping(target = "title", source = "dto.title")
    @Mapping(target = "weight", source = "dto.weight")
    @Mapping(target = "calories", source = "dto.calories")
    @Mapping(target = "proteins", source = "dto.proteins")
    @Mapping(target = "fats", source = "dto.fats")
    @Mapping(target = "carbohydrates", source = "dto.carbohydrates")
    Product updateProduct(CreateProductDto dto, Product oldProduct);

}
