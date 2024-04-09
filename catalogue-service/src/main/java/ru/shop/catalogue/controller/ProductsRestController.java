package ru.shop.catalogue.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.shop.catalogue.controller.payload.NewProductPayload;
import ru.shop.catalogue.entity.Product;
import ru.shop.catalogue.service.IProductService;

import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("catalogue-api/products")
public class ProductsRestController {

    private final IProductService productService;

    @GetMapping
    public List<Product> findProducts(){
        return this.productService.findAllProducts();
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
            Product product = this.productService.createProduct(payload.productName(), payload.description());
            return ResponseEntity
                    .created(uriComponentsBuilder
                            .replacePath("/catalogue-api/products/{productId}")
                            .build(Map.of("productId", product.getId())))
                    .body(product);
        }
    }
}
