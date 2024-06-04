package ru.shop.customer.repository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.shop.customer.entity.ProductReview;

public interface ProductReviewsRepository {

    Mono<ProductReview> save(ProductReview productReview);

    Flux<ProductReview> findAllByProductId(int productId);
}
