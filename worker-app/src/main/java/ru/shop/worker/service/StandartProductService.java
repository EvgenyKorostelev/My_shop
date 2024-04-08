package ru.shop.worker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.worker.entity.Product;
import ru.shop.worker.repository.IProductRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StandartProductService implements IProductService {
    private final IProductRepository productRepository;
    
    @Override
    public List<Product> findAllProducts() {
        return this.productRepository.findAll();
    }

    @Override
    public Product createProduct(String productName, String description) {
        return this.productRepository.save(new Product(null, productName, description));
    }

    @Override
    public Optional<Product> findAllProduct(int productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    public void updateProduct(Integer id, String productName, String description) {
        this.productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    product.setProductName(productName);
                    product.setDescription(description);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    public void deleteProduct(Integer id) {
        this.productRepository.deleteById(id);
    }
}
