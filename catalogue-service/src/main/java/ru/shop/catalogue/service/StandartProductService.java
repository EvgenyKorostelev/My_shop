package ru.shop.catalogue.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shop.catalogue.entity.Product;
import ru.shop.catalogue.repository.IProductRepository;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StandartProductService implements IProductService {

    private final IProductRepository productRepository;

    @Override
    public Iterable<Product> findAllProducts(String filter) {
        if(filter != null && !filter.isBlank()){
            return this.productRepository.findAllByTitleLikeIgnoreCase("%" + filter + "%");
        } else {
            return this.productRepository.findAll();
        }

    }

    @Override
    @Transactional
    public Product createProduct(String productName, String description) {
        return this.productRepository.save(new Product(null, productName, description));
    }

    @Override
    public Optional<Product> findAllProduct(int productId) {
        return this.productRepository.findById(productId);
    }

    @Override
    @Transactional
    public void updateProduct(Integer id, String title, String description) {
        this.productRepository.findById(id)
                .ifPresentOrElse(product -> {
                    product.setTitle(title);
                    product.setDescription(description);
                }, () -> {
                    throw new NoSuchElementException();
                });
    }

    @Override
    @Transactional
    public void deleteProduct(Integer id) {
        this.productRepository.deleteById(id);
    }
}
