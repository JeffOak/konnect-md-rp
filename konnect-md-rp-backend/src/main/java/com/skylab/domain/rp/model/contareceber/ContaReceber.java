package com.skylab.domain.rp.model.contareceber;

import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "creceber")
@Getter
public class ContaReceber {

    @Transient
    private Integer id = 0;
    @EmbeddedId
    private ContaReceberId contaReceberId;
    @Column(name = "valor")
    private BigDecimal valor;
    @Column(name = "data_vencimento")
    private Date vencimento;
    @Column(name = "documento")
    private String documento;

}
