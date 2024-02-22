package com.skylab.service.md.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class ErrorResponse {

    @JsonProperty
    private String retorno;
    @JsonProperty
    private String mensagem;

}
