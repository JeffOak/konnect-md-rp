package com.skylab.domain.rp.model.venda;

import com.skylab.domain.rp.model.FormaPagamento;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Table(name = "encerravendaitem")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EncerramentoVendaPagamento {

    @EmbeddedId
    private EncerramentoVendaPagamentoId id;
    @Column(name = "ite_003")
    private BigDecimal valor;
    @Column(name = "troco_dinheiro")
    private BigDecimal troco;
    @ManyToOne
    @JoinColumn(name = "id_formapgto")
    private FormaPagamento formaPagamento;
    @Column(name = "ite_002")
    private Timestamp data;

    // Campos desconhecidos
    @Column(name = "ite_004")
    private final int ite004 = 1;
    @Column(name = "ite_005")
    private final BigDecimal ite005 = BigDecimal.ZERO;

    public BigDecimal getValor() {
        return valor.add(troco);
    }
}
