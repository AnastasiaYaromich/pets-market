package ru.yaromich.pets.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yaromich.pets.market.api.PageDto;
import ru.yaromich.pets.market.api.ProductDto;
import ru.yaromich.pets.market.core.converters.PageConverter;
import ru.yaromich.pets.market.core.converters.ProductConverter;
import ru.yaromich.pets.market.core.entities.Product;
import ru.yaromich.pets.market.core.exceptions.ResourceNotFoundException;
import ru.yaromich.pets.market.core.repositories.specifications.ProductsSpecifications;
import ru.yaromich.pets.market.core.services.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;
    private final PageConverter pageConverter;

    @GetMapping
    public PageDto getAllProducts(@RequestParam(name = "p", defaultValue = "1") Integer page,
                                              @RequestParam(name = "page_size", defaultValue = "10") Integer pageSize,
                                              @RequestParam(name = "title_part", required = false) String titlePart,
                                              @RequestParam(name = "min_price", required = false) Integer minPrice,
                                              @RequestParam(name = "max_price", required = false) Integer maxPrice) {
        if(page < 1) {
            page = 1;
        }
        Specification<Product> spec = Specification.where(null);
        if(titlePart != null) {
            spec = spec.and(ProductsSpecifications.titleLike(titlePart));
        }
        if(minPrice != null) {
            spec = spec.and(ProductsSpecifications.priceGreaterOrEqualsThan(BigDecimal.valueOf(minPrice)));
        }
        if(maxPrice != null) {
            spec = spec.and(ProductsSpecifications.priceLessThanOrEqualsThan(BigDecimal.valueOf(maxPrice)));
        }

        PageDto pageDto = pageConverter.entityToDto(productService.findAll(page - 1, pageSize, spec).map(productConverter::entityToDto));
        return pageDto;
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productConverter.entityToDto(productService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + id + " не найден")));
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody ProductDto productDto) {
        productService.createNewProduct(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
