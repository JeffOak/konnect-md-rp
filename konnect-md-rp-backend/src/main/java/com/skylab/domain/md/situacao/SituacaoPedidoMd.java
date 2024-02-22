package com.skylab.domain.md.situacao;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum SituacaoPedidoMd {

    VERIFICANDO("1"),
    NA_FILA_PREPARO("2"),
    EM_ANDAMENTO("3"),
    PRONTO_PARA_ENTREGA("4"),
    SAIU_PARA_ENTREGA("5"),
    ENTREGA_REALIZADA("6"),
    ENTREGADOR_NA_PORTA("7"),
    CANCELADO("9");

    @JsonValue
    private final String value;

    SituacaoPedidoMd(String value) {
        this.value = value;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static SituacaoPedidoMd get(String value) {
        for (SituacaoPedidoMd situacaoPedidoMd : SituacaoPedidoMd.values()) {
            if (situacaoPedidoMd.getValue().equals(value)) {
                return situacaoPedidoMd;
            }
        }
        return null;
    }
}
