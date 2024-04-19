package ru.shop.worker.client;

import ru.shop.worker.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductsRestClient {

    List<Product> findAllProducts(String filter);

    Product createProduct(String title, String description);

    Optional<Product> findProduct(int productId);

    void updateProduct(int productId, String title, String description);

    void deleteProduct(int productId);
}
