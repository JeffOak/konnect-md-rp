package com.skylab.domain.rp.model.venda;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemVendaOpcionalId implements Serializable {

    @Column(name = "id_venda")
    private int idVenda;
    @Column(name = "id_vendaitem")
    private int idVendaItem;
    @Column(name = "id_opcional")
    private int idOpcional;
    @Column(name = "id_empresa")
    private final int empresaId = 1;

}
