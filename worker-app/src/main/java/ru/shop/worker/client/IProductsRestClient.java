package ru.shop.worker.client;

import ru.shop.worker.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductsRestClient {

    List<Product> findAllProducts();

    Product createProduct(String nameProduct, String description);

    Optional<Product> findProduct(int productId);

    void updateProduct(int productId, String productName, String description);

    void deleteProduct(int productId);
}
