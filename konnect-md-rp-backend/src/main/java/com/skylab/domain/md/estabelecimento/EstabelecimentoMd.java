package com.skylab.domain.md.estabelecimento;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class EstabelecimentoMd {

    @JsonProperty("dsNome")
    private String nome;
    @JsonProperty("flgAberto")
    private int aberto;

}
