package ru.shop.customer.repository;

import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.shop.customer.entity.ProductReview;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


@Repository
public class InMemoryProductReviewsRepository implements ProductReviewsRepository {

    private final List<ProductReview> productReviews = Collections.synchronizedList(new LinkedList<>());

    @Override
    public Mono<ProductReview> save(ProductReview productReview) {
        this.productReviews.add(productReview);
        return Mono.just(productReview);
    }

    @Override
    public Flux<ProductReview> findAllByProductId(int productId) {
        return Flux.fromIterable(this.productReviews)
                .filter(productReview -> productReview.getProductId() == productId);
    }
}
