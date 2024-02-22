package com.skylab.domain.md.pedido;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class    ItemPedidoMd {

    @JsonProperty("dsCategoria")
    private String categoria;
    @JsonProperty("dsNome")
    private String nome;
    @JsonProperty("vlItem")
    private String valor;
    @JsonProperty("qtItens")
    private String quantidade;
    @JsonProperty("vlTotalItem")
    private String total;
    @JsonProperty("vlDescontoItemTotal")
    private String desconto;
    @JsonProperty("arrAdicionais")
    private List<AdicionalPedidoMd> adicionais;
    @JsonProperty("dsObservacao")
    private String observacao;

}
