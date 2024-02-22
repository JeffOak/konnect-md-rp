package com.skylab.domain.rp.model.venda.converter;

import com.skylab.domain.rp.model.venda.TipoFiscal;

import javax.persistence.AttributeConverter;

public class TipoFiscalConverter implements AttributeConverter<TipoFiscal, String> {
    @Override
    public String convertToDatabaseColumn(TipoFiscal attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public TipoFiscal convertToEntityAttribute(String dbData) {
        TipoFiscal tipoFiscal = TipoFiscal.get(dbData);
        return tipoFiscal != null ? tipoFiscal : TipoFiscal.NENHUM;
    }
}
