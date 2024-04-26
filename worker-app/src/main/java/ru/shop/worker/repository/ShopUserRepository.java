package ru.shop.worker.repository;

import org.springframework.data.repository.CrudRepository;
import ru.shop.worker.entity.ShopUser;

import java.util.Optional;

public interface ShopUserRepository extends CrudRepository<ShopUser, Integer> {

    Optional<ShopUser> findByUsername(String username);
}
