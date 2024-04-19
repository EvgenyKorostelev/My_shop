package ru.shop.catalogue.service;

import ru.shop.catalogue.entity.Product;

import java.util.Optional;

public interface IProductService {
    Iterable<Product> findAllProducts(String filter);

    Product createProduct(String s, String description);

    Optional<Product> findAllProduct(int productId);

    void updateProduct(Integer id, String s, String description);

    void deleteProduct(Integer id);
}
