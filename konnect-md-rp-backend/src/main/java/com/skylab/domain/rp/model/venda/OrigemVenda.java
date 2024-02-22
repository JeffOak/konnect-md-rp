package com.skylab.domain.rp.model.venda;

import lombok.Getter;

@Getter
public enum OrigemVenda {

    DELIVERY("D"),
    BALCAO("B"),
    PDV("P"),
    MESA("M"),
    COMANDA("C");


    private final String value;

    OrigemVenda(String value) {
        this.value = value;
    }

    public static OrigemVenda get(String value) {
        for(OrigemVenda origemVenda : OrigemVenda.values()){
            if(origemVenda.getValue().equals(value)) {
                return origemVenda;
            }
        }
        return null;
    }
}
