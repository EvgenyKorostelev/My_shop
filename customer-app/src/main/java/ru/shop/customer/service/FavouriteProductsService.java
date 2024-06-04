package ru.shop.customer.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.shop.customer.entity.FavouriteProduct;

public interface FavouriteProductsService {

    Mono<FavouriteProduct> addProductToFavourites(int productId);

    Mono<Void> removeProductFromFavourites(int productId);

    Mono<FavouriteProduct> findFavouriteProductByProduct(int productId);

    Flux<FavouriteProduct> findFavouriteProducts();
}
