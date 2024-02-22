package com.skylab.service.md.api.model.produto.ativardesativar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class MdProdutoAtivarDesativar {

    @JsonProperty
    private Integer idCardapio;
    @JsonProperty("flgAtivo")
    private Integer ativo;

}
