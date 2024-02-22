package com.skylab.domain.rp.repository;

import com.skylab.domain.rp.model.cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "SELECT COALESCE(MAX(CLI_001), 0) FROM CLIENTES", nativeQuery = true)
    Integer getMaxId();

}
