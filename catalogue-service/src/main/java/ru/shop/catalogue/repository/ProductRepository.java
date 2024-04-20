package ru.shop.catalogue.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.shop.catalogue.entity.Product;


public interface ProductRepository extends CrudRepository<Product, Integer> {

    @Query(name = "Product.Product.findByTitleLikeIgnoringCase", nativeQuery = true)
    Iterable<Product> findAllByTitleLikeIgnoreCase(@Param("filter") String filter); //select * from catalogue.t_product where c_title ilike : filter

}
