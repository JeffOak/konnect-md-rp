package com.skylab.service.md.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum Operacao {

    INFORMACOES_ESTABELECIMENTO(1),
    LISTAR_PRODUTOS(5),
    CADASTRAR_PRODUTO(9),
    ALTERAR_VALOR(11),
    ATIVAR_DESATIVAR(10),
    LISTAR_PEDIDOS(2),
    LISTAR_SITUACOES(7),
    DETALHE_PEDIDO(2);

    @JsonValue
    private final int value;

    Operacao(int value) {
        this.value = value;
    }

    @JsonCreator
    public static Operacao getOperacao(int value) {
        for (Operacao operacao : Operacao.values()) {
            if (operacao.getValue() == value) {
                return operacao;
            }
        }
        return null;
    }

}
