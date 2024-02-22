package com.skylab.service.md.api.model.estabelecimento;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skylab.service.md.api.model.Operacao;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MdEstabelecimentoRequest {

    @JsonProperty
    private final Operacao nrOperacao = Operacao.INFORMACOES_ESTABELECIMENTO;
    @JsonProperty
    private boolean sandBox;
    @JsonProperty
    private int idEmpresaIntegrada;
    @JsonProperty
    private int idOrigem;
    @JsonProperty
    private int idRestaurante;

}
