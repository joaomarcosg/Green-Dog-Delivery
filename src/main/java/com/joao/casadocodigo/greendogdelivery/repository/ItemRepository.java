package com.joao.casadocodigo.greendogdelivery.repository;

import com.joao.casadocodigo.greendogdelivery.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
