package com.skylab.domain.rp.model.venda;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "encerravenda")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EncerramentoVenda {

    @Id
    @Column(name = "enc_001")
    private int encerramentoId;
    @Column(name = "emp_001")
    private final int empresaId = 1;
    @Column(name = "ven_001")
    private int vendaId;
    @Column(name = "enc_003")
    private BigDecimal total;
    @Column(name = "enc_006")
    private BigDecimal acrescimo;
    @Column(name = "for_001")
    private int formaPagamentoId;
    @Column(name = "sit_001")
    private final int situacao = 1;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "enc_001")
    private List<EncerramentoVendaPagamento> formasPagamento;
    @Column(name = "enc_002")
    private Timestamp data;

    // Campos desconhecidos
    @Column(name = "cli_001")
    private final int cli001 = 0;
    @Column(name = "enc_007")
    private final BigDecimal enc007 = BigDecimal.ZERO;
}
