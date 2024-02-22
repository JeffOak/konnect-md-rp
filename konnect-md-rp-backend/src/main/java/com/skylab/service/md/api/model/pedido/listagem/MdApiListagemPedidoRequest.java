package com.skylab.service.md.api.model.pedido.listagem;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skylab.domain.md.situacao.SituacaoPedidoMd;
import com.skylab.service.md.api.model.Operacao;
import lombok.Builder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MdApiListagemPedidoRequest {

    @JsonProperty
    private final Operacao nrOperacao = Operacao.LISTAR_PEDIDOS;
    @JsonProperty
    private boolean sandBox;
    @JsonProperty
    private int idEmpresaIntegrada;
    @JsonProperty
    private int idOrigem;
    @JsonProperty
    private int idRestaurante;
    @JsonProperty("dtInicio")
    private String dataInicio;
    @JsonProperty("dtFim")
    private String dataFim;
    @JsonProperty
    private List<SituacaoPedidoMd> idSituacao;

}
