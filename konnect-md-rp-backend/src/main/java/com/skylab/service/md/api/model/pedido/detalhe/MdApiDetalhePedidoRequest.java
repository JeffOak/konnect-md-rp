package com.skylab.service.md.api.model.pedido.detalhe;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skylab.service.md.api.model.Operacao;
import lombok.Builder;

import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MdApiDetalhePedidoRequest {

    @JsonProperty
    private final Operacao nrOperacao = Operacao.DETALHE_PEDIDO;
    @JsonProperty
    private boolean sandBox;
    @JsonProperty
    private int idEmpresaIntegrada;
    @JsonProperty
    private int idOrigem;
    @JsonProperty
    private int idRestaurante;
    @JsonProperty
    private List<Integer> idPedido;
    @JsonProperty("dtInicio")
    private String dataInicio;
    @JsonProperty("dtFim")
    private String dataFim;
}
