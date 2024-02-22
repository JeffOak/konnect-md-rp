package com.skylab.domain.md.produto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
public class ProdutoMd {

    @JsonProperty("idCardapio")
    private Long id;
    @JsonProperty("dsNome")
    private String nome;
    @JsonProperty("dsDescricao")
    private String descricao;
    @JsonProperty("dsCategoria")
    private String categoria;
    @JsonProperty("dsImagem")
    private String urlImagem;
    @JsonProperty("vlItem")
    private String valor;
    @JsonProperty("qtEstoque")
    private BigDecimal quantidadeEstoque;
    @JsonProperty("flgAtivo")
    private int ativo;
}
