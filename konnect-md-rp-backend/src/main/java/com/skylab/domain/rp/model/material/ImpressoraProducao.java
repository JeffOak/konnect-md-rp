package com.skylab.domain.rp.model.material;

import lombok.Getter;

@Getter
public enum ImpressoraProducao {

    NENHUMA(0),
    BALCAO(1),
    COZINHA(2),
    BAR(3),
    SALAO(4),
    AMBIENTE(5),
    CHOPEIRA(6),
    EXTRA_1(7),
    EXTRA_2(8);

    private final int id;

    ImpressoraProducao(int id) {
        this.id = id;
    }

    public static ImpressoraProducao get(int id) {
        for (ImpressoraProducao impressoraProducao : ImpressoraProducao.values()) {
            if (impressoraProducao.getId() == id) {
                return impressoraProducao;
            }
        }
        return null;
    }
}
