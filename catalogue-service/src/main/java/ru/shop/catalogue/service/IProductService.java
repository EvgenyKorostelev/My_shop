package ru.shop.catalogue.service;

import ru.shop.catalogue.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {
    List<Product> findAllProducts();

    Product createProduct(String s, String description);

    Optional<Product> findAllProduct(int productId);

    void updateProduct(Integer id, String s, String description);

    void deleteProduct(Integer id);
}
