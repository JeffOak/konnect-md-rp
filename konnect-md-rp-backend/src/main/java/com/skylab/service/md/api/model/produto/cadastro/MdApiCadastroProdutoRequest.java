package com.skylab.service.md.api.model.produto.cadastro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skylab.domain.md.produto.ProdutoMd;
import com.skylab.service.md.api.model.Operacao;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MdApiCadastroProdutoRequest {

    @JsonProperty
    private final Operacao nrOperacao = Operacao.CADASTRAR_PRODUTO;
    @JsonProperty
    private boolean sandBox;
    @JsonProperty
    private int idEmpresaIntegrada;
    @JsonProperty
    private int idOrigem;
    @JsonProperty
    private int idRestaurante;
    @JsonProperty
    private List<ProdutoMd> produtos;

}
