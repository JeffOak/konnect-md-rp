package com.skylab.domain.rp.model.venda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EncerramentoVendaPagamentoId implements Serializable {

    @Column(name = "ite_001")
    private int sequencial;
    @Column(name = "enc_001")
    private int encerramentoId;
    @Column(name = "emp_001")
    private final int empresaId = 1;

}

