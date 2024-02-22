package com.skylab.domain.rp.model.venda;

import lombok.Getter;

@Getter
public enum StatusVenda {

    ATIVA(1, "Ativa"),
    CANCELADA(2, "Cancelada"),
    EM_PROCESSO(8, "Em Processo");

    private final int value;
    private final String name;

    StatusVenda(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static StatusVenda get(int value) {
        for (StatusVenda status : StatusVenda.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
