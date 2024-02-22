package com.skylab.domain.rp.repository;

import com.skylab.domain.rp.model.venda.VendaPagamentoAntecipado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VendaPagamentoAntecipadoRepository extends JpaRepository<VendaPagamentoAntecipado, Integer> {

    @Query(value = "SELECT COALESCE(MAX(ID_VENDA_PAG_ANTECIPADO), 0) FROM VENDA_PAG_ANTECIPADO", nativeQuery = true)
    Integer getMaxId();

}
