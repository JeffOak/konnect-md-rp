package com.skylab.domain.mdrp.model.pedido;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.skylab.domain.md.situacao.SituacaoPedidoMd;
import com.skylab.domain.rp.model.venda.StatusVenda;
import com.skylab.domain.rp.model.venda.converter.StatusVendaConverter;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "PEDIDOS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PedidoMdRp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "RP_ID")
    private Integer rpId;
    @Column(name = "MD_ID")
    @JsonProperty
    private String mdId;
    @Column(name = "STATUS_RP")
    @Convert(converter = StatusVendaConverter.class)
    private StatusVenda statusRp;
    @Column(name = "STATUS_MD")
    private SituacaoPedidoMd statusMd;
    @Column(name = "mensagem_enviada")
    private boolean mensagemEnviada;
    @Column
    private boolean erro;
    @Column(name = "JSON_MD")
    private String jsonMd;
    @JsonProperty
    @Column(name = "DESCRICAO_ERRO")
    private String descricao_erro;
}
