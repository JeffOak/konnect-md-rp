package com.skylab.domain.rp.repository;

import com.skylab.domain.rp.model.cliente.Cliente;
import com.skylab.domain.rp.model.venda.EncerramentoVenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EncerraVendaRepository extends JpaRepository<EncerramentoVenda, Integer> {

    @Query(value = "SELECT COALESCE(MAX(ENC_001), 0) FROM ENCERRAVENDA", nativeQuery = true)
    Integer getMaxId();

}
