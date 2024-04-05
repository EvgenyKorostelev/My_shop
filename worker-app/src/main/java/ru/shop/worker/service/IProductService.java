package ru.shop.worker.service;

import ru.shop.worker.entity.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAllProducts();

    Product createProduct(String s, String description);
}
