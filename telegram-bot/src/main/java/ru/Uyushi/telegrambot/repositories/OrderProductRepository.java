package ru.Uyushi.telegrambot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.Uyushi.telegrambot.entities.OrderProduct;

@RepositoryRestResource(collectionResourceRel = "orderProducts", path = "orderProducts")
public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {
}
