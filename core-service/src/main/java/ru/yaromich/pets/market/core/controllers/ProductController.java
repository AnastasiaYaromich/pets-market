package ru.yaromich.pets.market.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yaromich.pets.market.api.ProductDto;
import ru.yaromich.pets.market.core.converters.ProductConverter;
import ru.yaromich.pets.market.core.entities.Product;
import ru.yaromich.pets.market.core.exceptions.ResourceNotFoundException;
import ru.yaromich.pets.market.core.services.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final ProductConverter productConverter;

    @GetMapping
    public List<ProductDto> getAllProducts() {
        List<Product> products = productService.findAll();
        return productConverter.entityListToDtoList(products);
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