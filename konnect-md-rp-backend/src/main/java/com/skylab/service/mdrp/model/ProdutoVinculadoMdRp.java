package com.skylab.service.mdrp.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoVinculadoMdRp {

    @JsonProperty("id")
    private Integer idRp;
    private String descricao;
    private String idMd;

}
