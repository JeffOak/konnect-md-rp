package com.skylab.service.md.api.model.produto.alteracaovalor;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class MdProdutoAlterarValor {

    @JsonProperty
    private Integer idCardapio;
    @JsonProperty("vlItem")
    private String valor;

}
