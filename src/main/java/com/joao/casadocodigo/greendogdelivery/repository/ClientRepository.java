package com.joao.casadocodigo.greendogdelivery.repository;

import com.joao.casadocodigo.greendogdelivery.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
