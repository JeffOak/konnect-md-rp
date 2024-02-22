package com.skylab.domain.rp.model.venda;

import lombok.Getter;

@Getter
public enum TipoFiscal {

    NENHUM("NENHUM"),
    SAT("SAT"),
    MFE("MFE"),
    NFCE("NFCE");

    private final String value;

    TipoFiscal(String value) {
        this.value = value;
    }

    public static TipoFiscal get(String value) {
        for (TipoFiscal tipoFiscal : TipoFiscal.values()) {
            if (tipoFiscal.value.equals(value)) {
                return tipoFiscal;
            }
        }
        return null;
    }
}
