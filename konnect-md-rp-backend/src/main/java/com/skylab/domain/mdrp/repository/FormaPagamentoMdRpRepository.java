package com.skylab.domain.mdrp.repository;

import com.skylab.domain.mdrp.model.FormaPagamentoMdRp;
import com.skylab.domain.mdrp.model.ProdutoMdRp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FormaPagamentoMdRpRepository extends JpaRepository<FormaPagamentoMdRp, Integer> {

    Optional<FormaPagamentoMdRp> findByidRp(Integer rpId);

    Optional<FormaPagamentoMdRp> findByIdMd(String mdId);

}
