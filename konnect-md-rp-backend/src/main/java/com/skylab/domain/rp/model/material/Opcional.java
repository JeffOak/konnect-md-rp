package com.skylab.domain.rp.model.material;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Opcional {

    @Id
    @Column(name = "id_opcional")
    private Integer id;
    @Column(name = "id_empresa")
    private final int empresaId = 1;
    private String descricao;
    private BigDecimal valor;
    @Column(name = "id_situacao")
    private final int situacao = 4;
    private final int tipo = 0;

}
