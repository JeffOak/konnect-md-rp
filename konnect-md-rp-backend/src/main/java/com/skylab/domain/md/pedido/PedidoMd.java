package com.skylab.domain.md.pedido;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skylab.domain.md.situacao.SituacaoPedidoMd;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
public class PedidoMd {

    @JsonProperty
    private String idPedido;
    @JsonProperty("dtPedido")
    private String data;
    @JsonProperty("idSituacao")
    private SituacaoPedidoMd situacao;
    @JsonProperty("dsNome")
    private String nomeCliente;
    @JsonProperty("dsEndereco")
    private String endereco;
    @JsonProperty("dsObservacao")
    private String observacao;
    @JsonProperty("vlEntrega")
    private String taxaEntrega;
    @JsonProperty("vlTotal")
    private String total;
    @JsonProperty("dsDetalhe")
    private List<ItemPedidoMd> itens;
    @JsonProperty("dsPagamento")
    private FormaPagamentoMd formaPagamento;

}
