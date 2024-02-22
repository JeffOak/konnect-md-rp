package com.skylab.service.md.api.model.produto.listagem;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skylab.service.md.api.model.Operacao;
import lombok.Builder;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MdApiListagemProdutoRequest {

    @JsonProperty
    private final Operacao nrOperacao = Operacao.LISTAR_PRODUTOS;
    @JsonProperty
    private boolean sandBox;
    @JsonProperty
    private int idEmpresaIntegrada;
    @JsonProperty
    private int idOrigem;
    @JsonProperty
    private int idRestaurante;

}
