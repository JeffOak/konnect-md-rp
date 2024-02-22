package com.skylab.service.md.api.model.produto.ativardesativar;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skylab.service.md.api.model.Operacao;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MdApiAtivarDesativarProdutoRequest {

    @JsonProperty
    private final Operacao nrOperacao = Operacao.ATIVAR_DESATIVAR;
    @JsonProperty
    private boolean sandBox;
    @JsonProperty
    private int idEmpresaIntegrada;
    @JsonProperty
    private int idOrigem;
    @JsonProperty
    private int idRestaurante;
    @JsonProperty
    private List<MdProdutoAtivarDesativar> produtos;

}
