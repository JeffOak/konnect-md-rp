package com.skylab.domain.mdrp.repository;

import com.skylab.domain.mdrp.model.ProdutoMdRp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProdutoMdRpRepository extends JpaRepository<ProdutoMdRp, Integer> {

    Optional<ProdutoMdRp> findByRpId(Integer rpId);

    Optional<ProdutoMdRp> findByMdId(Integer mdId);

}
