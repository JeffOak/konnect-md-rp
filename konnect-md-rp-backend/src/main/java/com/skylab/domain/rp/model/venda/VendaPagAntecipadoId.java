package com.skylab.domain.rp.model.venda;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VendaPagAntecipadoId implements Serializable {

    @Column(name = "id_venda_pag_antecipado")
    private int id;
    @Column(name = "id_venda")
    private int vendaId;
    @Column(name = "id_empresa")
    private final int empresaId = 1;

}
