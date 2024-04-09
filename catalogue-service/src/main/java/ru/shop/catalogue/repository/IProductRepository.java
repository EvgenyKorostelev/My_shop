package ru.shop.catalogue.repository;

import ru.shop.catalogue.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    List<Product> findAll();

    Product save(Product product);

    Optional<Product> findById(Integer productId);

    void deleteById(Integer id);
}
