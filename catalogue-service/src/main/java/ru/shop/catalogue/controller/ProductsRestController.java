package ru.shop.catalogue.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.shop.catalogue.controller.payload.NewProductPayload;
import ru.shop.catalogue.entity.Product;
import ru.shop.catalogue.service.IProductService;


import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/products")
public class ProductsRestController {

    private final IProductService productService;

    @GetMapping
    public Iterable<Product> findProducts(@RequestParam(name = "filter", required = false) String filter){
        return this.productService.findAllProducts(filter);
    }

    @PostMapping()
    public ResponseEntity <?> createProduct(@Valid @RequestBody NewProductPayload payload,
                                                  BindingResult bindingResult,
                                                  UriComponentsBuilder uriComponentsBuilder)
            throws BindException{
        if(bindingResult.hasErrors()){
            if(bindingResult instanceof  BindException exception){
                throw exception;
            }else {
                throw new BindException(bindingResult);
            }
        }else {
            Product product = this.productService.createProduct(payload.title(), payload.description());
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/catalogue-api/products/{productId}")
                            .build(Map.of("productId", product.getId())))
                    .body(product);
        }
    }
}
