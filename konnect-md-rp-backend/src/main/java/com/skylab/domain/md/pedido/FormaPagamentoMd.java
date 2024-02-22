package com.skylab.domain.md.pedido;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum     FormaPagamentoMd {

    PAGO_ONLINE(1, "Pago Online"),
    COBRAR_NO_DINHEIRO(2, "Cobrar no Dinheiro");


    private final int id;
    @JsonValue
    private final String value;

    FormaPagamentoMd(int id, String value) {
        this.id = id;
        this.value = value;
    }

    public static FormaPagamentoMd get(int id) {
        for (FormaPagamentoMd pagamentoMd : FormaPagamentoMd.values()) {
            if (pagamentoMd.getId() == id) {
                return pagamentoMd;
            }
        }
        return null;
    }

    @JsonCreator(mode = JsonCreator.Mode.DELEGATING)
    public static FormaPagamentoMd get(String value) {
        for (FormaPagamentoMd pagamentoMd : FormaPagamentoMd.values()) {
            if (pagamentoMd.getValue().equals(value)) {
                return pagamentoMd;
            }
        }
        return null;
    }
}
