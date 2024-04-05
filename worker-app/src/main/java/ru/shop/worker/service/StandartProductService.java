package ru.shop.worker.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.shop.worker.entity.Product;
import ru.shop.worker.repository.IProductRepository;

import java.util.List;

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
}
