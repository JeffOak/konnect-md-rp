package com.skylab.domain.rp.repository;

import com.skylab.domain.rp.model.venda.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VendaRepository extends JpaRepository<Venda, Integer> {

    @Query(value = "SELECT COALESCE(MAX(VEN_001), 0) FROM VENDA", nativeQuery = true)
    Integer getMaxId();
}

