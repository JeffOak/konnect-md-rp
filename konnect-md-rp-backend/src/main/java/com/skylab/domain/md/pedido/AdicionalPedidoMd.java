package com.skylab.domain.md.pedido;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class AdicionalPedidoMd {

    @JsonProperty("qtItens")
    private String quantidade;
    @JsonProperty("dsNome")
    private String nome;
    @JsonProperty("vlItem")
    private String valor;
    @JsonProperty("vlTotal")
    private String total;
}
