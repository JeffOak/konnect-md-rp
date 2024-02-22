package com.skylab.domain.rp.model.material;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "materiais_opcional")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MaterialOpcional {

    @EmbeddedId
    private MaterialOpcionalId id;
    @Column(name = "id_empresa")
    private final int empresaId = 1;

}
