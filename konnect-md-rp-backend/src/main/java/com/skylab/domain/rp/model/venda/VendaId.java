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
@NoArgsConstructor
@AllArgsConstructor
public class VendaId implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ven_001")
    private int vendaId;
    @Column(name = "emp_001")
    private int empresaId;
}
