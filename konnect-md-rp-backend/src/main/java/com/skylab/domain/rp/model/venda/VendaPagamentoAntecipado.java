package com.skylab.domain.rp.model.venda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "venda_pag_antecipado")
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VendaPagamentoAntecipado {


    @EmbeddedId
    private VendaPagAntecipadoId id;
    @Column(name = "id_formapgto")
    private int idFormaPagamento;
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "data_hora")
    private Timestamp data;
    @Column(name = "id_situacao")
    private final int situacao = 4;

}
