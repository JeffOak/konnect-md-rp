package com.skylab.domain.rp.model.material;

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
public class MaterialOpcionalId implements Serializable {

    @Column(name = "id_material")
    private int materialId;
    @Column(name = "id_opcional")
    private int idOpcional;

}
