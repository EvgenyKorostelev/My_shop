package ru.shop.customer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.shop.customer.entity.ProductReview;
import ru.shop.customer.repository.ProductReviewsRepository;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DefaultProductReviewsService implements ProductReviewsService {

    private final ProductReviewsRepository productReviewsRepository;



    @Override
    public Mono<ProductReview> createProductReview(int productId, int rating, String review) {
        return this.productReviewsRepository.save(new ProductReview(UUID.randomUUID(), productId, rating, review));
    }

    @Override
    public Flux<ProductReview> findProductReviewsByProduct(int productId) {
        return this.productReviewsRepository.findAllByProductId(productId);
    }
}
