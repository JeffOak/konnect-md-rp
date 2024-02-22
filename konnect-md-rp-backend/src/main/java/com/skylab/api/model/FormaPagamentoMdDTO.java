package com.skylab.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class FormaPagamentoMdDTO {

    @JsonProperty
    public int id;
    @JsonProperty
    public String descricao;
}
