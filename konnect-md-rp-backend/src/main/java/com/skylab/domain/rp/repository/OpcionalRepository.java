package com.skylab.domain.rp.repository;

import com.skylab.domain.rp.model.material.Opcional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OpcionalRepository extends JpaRepository<Opcional, Integer> {

    @Query(value = "SELECT COALESCE(MAX(ID_OPCIONAL), 0) FROM OPCIONAL", nativeQuery = true)
    Integer getMaxId();

    List<Opcional> findByDescricao(String descricao);
}
