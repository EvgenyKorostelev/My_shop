package ru.shop.worker.repository;

import ru.shop.worker.entity.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();

    Product save(Product product);
}
