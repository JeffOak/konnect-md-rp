package com.skylab.domain.mdrp.repository;

import com.skylab.domain.mdrp.model.pedido.PedidoMdRp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface PedidoMdRpRepository extends JpaRepository<PedidoMdRp, Integer> {

    Optional<PedidoMdRp> findByRpId(Integer rpId);

    Optional<PedidoMdRp> findByMdId(String rpId);

    List<PedidoMdRp> findByErro(boolean erro);

}
