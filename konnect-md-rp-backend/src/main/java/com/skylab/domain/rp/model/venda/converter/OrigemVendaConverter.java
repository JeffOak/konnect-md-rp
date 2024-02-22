package com.skylab.domain.rp.model.venda.converter;

import com.skylab.domain.rp.model.venda.OrigemVenda;

import javax.persistence.AttributeConverter;

public class OrigemVendaConverter implements AttributeConverter<OrigemVenda, String> {
    @Override
    public String convertToDatabaseColumn(OrigemVenda attribute) {
        return attribute.getValue();
    }

    @Override
    public OrigemVenda convertToEntityAttribute(String dbData) {
        return OrigemVenda.get(dbData);
    }
}
