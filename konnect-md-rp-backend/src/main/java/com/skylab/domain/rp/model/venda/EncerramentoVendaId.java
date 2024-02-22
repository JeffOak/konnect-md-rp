package com.skylab.domain.rp.model.venda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Embeddable
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EncerramentoVendaId implements Serializable {

    @Column(name = "enc_001")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int encerramentoId;
}
