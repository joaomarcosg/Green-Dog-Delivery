package com.joao.casadocodigo.greendogdelivery.repository;

import com.joao.casadocodigo.greendogdelivery.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
