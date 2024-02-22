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
public class ItemVendaId implements Serializable {

    @Column(name = "ite_001")
    private int id;
    @Column(name = "ven_001")
    private int vendaId;

}

