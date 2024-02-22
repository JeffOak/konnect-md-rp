package com.skylab.domain.rp.model.venda;

import com.skylab.domain.rp.model.material.ImpressoraProducao;
import com.skylab.domain.rp.model.material.Opcional;
import com.skylab.domain.rp.model.material.converter.ImpressoraProducaoConverter;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "VENDAITEM")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemVenda {

    @EmbeddedId
    private ItemVendaId itemVendaId;
    @Column(name = "emp_001")
    private final int empresa = 1;
    @Column(name = "sit_001")
    private int situacao;
    @Column(name = "ite_002")
    private BigDecimal quantidade;
    @Column(name = "ite_003")
    private BigDecimal valor;
    @Column(name = "ite_005")
    private BigDecimal total;
    private BigDecimal desconto;
    private BigDecimal acrescimo;
    @Column(name = "ite_006")
    private String observacao;
    @Column(name = "mat_001")
    private int materialId;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vendaitem")
    @JoinColumn(name = "id_venda")
    private List<ItemVendaOpcional> opcionais;
    // Flags de impressão
    @Column(name = "ite_008")
    private final String ite008 = "N";
    @Column(name = "ite_011")
    private final String ite011 = "S";
    @Column(name = "ite_013")
    @Convert(converter = ImpressoraProducaoConverter.class)
    private ImpressoraProducao impressora1;
    @Column(name = "ite_014")
    @Convert(converter = ImpressoraProducaoConverter.class)
    private ImpressoraProducao impressora2;

    public BigDecimal getAcrescimo() {
        return acrescimo != null ? acrescimo : BigDecimal.ZERO;
    }

    public BigDecimal getDesconto() {
        return desconto != null ? desconto : BigDecimal.ZERO;
    }

}
