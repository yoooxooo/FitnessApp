package app.it_academy.fitnessAppProducts.service;

import app.it_academy.fitnessAppProducts.core.audit.annotations.Audited;
import app.it_academy.fitnessAppProducts.core.dto.pageDto.PageDto;
import app.it_academy.fitnessAppProducts.core.dto.productDto.CreateProductDto;
import app.it_academy.fitnessAppProducts.core.dto.productDto.ProductDto;
import app.it_academy.fitnessAppProducts.core.exceptions.ErrorObject;
import app.it_academy.fitnessAppProducts.core.exceptions.custom.FieldValidationException;
import app.it_academy.fitnessAppProducts.dao.ProductRepository;
import app.it_academy.fitnessAppProducts.domain.Product;
import app.it_academy.fitnessAppProducts.mappers.PageMapper;
import app.it_academy.fitnessAppProducts.mappers.ProductMapper;
import app.it_academy.fitnessAppProducts.service.api.IProductService;
import jakarta.persistence.OptimisticLockException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class ProductService implements IProductService {

    private final ProductRepository repository;

    private final ProductMapper mapper;

    private final PageMapper<ProductDto> pageMapper;

    public ProductService(ProductRepository repository, ProductMapper mapper, PageMapper<ProductDto> pageMapper) {
        this.repository = repository;
        this.mapper = mapper;
        this.pageMapper = pageMapper;
    }

    @Override
    public PageDto<ProductDto> getAllProducts(Integer pageNumber, Integer pageSize) {
        if (pageNumber < 0 || pageSize < 1) {
            throw new IllegalArgumentException("Страницы с такими параметрами не существует");
        }
        Page<Product> productPage;
        if ((productPage = repository.findAll(PageRequest.of(pageNumber, pageSize))).getTotalPages() < pageNumber + 1) {
            throw new IllegalArgumentException("Общее количество страниц меньше чем номер запрашиваемой");
        }
        return pageMapper.toDto(productPage.map(mapper::createDto));
    }

    @Override
    @Audited(operationType = "CREATION", essenceType = "PRODUCT")
    public UUID createProduct(CreateProductDto productDto) {
        List<ErrorObject> errors;
        if ((errors = productDto.checkFields()).isEmpty()) {
            return repository.save(mapper.createEntity(productDto)).getId();
        } else {
            throw new FieldValidationException("Validation Error", errors);
        }
    }

    @Override
    @Audited(operationType = "UPDATE", essenceType = "PRODUCT")
    public UUID updateProduct(UUID uuid, Instant updateLastTime, CreateProductDto productDto) {
        Product bufferedProduct = findById(uuid);
        Long checkUpdateTimeLong = bufferedProduct.getUpdateDate().getEpochSecond();
        Long lastUpdateTimeLong = updateLastTime.getEpochSecond();
        if (lastUpdateTimeLong.equals(checkUpdateTimeLong)) {
            return repository.save(mapper.updateProduct(productDto.combine(bufferedProduct), bufferedProduct)).getId();
        } else throw new OptimisticLockException("Сущность уже была изменена");
    }

    @Override
    public Product findById(UUID uuid) {
        return repository.findById(uuid).orElseThrow();
    }

}
