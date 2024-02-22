package com.skylab.domain.rp.model.venda.converter;

import com.skylab.domain.rp.model.venda.StatusVenda;

import javax.persistence.AttributeConverter;

public class StatusVendaConverter implements AttributeConverter<StatusVenda, Integer> {
    @Override
    public Integer convertToDatabaseColumn(StatusVenda attribute) {
        return attribute.getValue();
    }

    @Override
    public StatusVenda convertToEntityAttribute(Integer dbData) {
        return StatusVenda.get(dbData);
    }
}
