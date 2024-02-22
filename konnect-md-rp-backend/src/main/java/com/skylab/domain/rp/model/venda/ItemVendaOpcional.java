package com.skylab.domain.rp.model.venda;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "vendaitemopcional")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ItemVendaOpcional {

    @EmbeddedId
    private ItemVendaOpcionalId id;
    private boolean gratis;
    private BigDecimal valor;
}
