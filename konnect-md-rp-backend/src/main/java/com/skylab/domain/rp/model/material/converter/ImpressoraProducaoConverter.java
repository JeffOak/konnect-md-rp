package com.skylab.domain.rp.model.material.converter;

import com.skylab.domain.rp.model.material.ImpressoraProducao;

import javax.persistence.AttributeConverter;

public class ImpressoraProducaoConverter implements AttributeConverter<ImpressoraProducao, Integer> {


    @Override
    public Integer convertToDatabaseColumn(ImpressoraProducao attribute) {
        return attribute.getId();
    }

    @Override
    public ImpressoraProducao convertToEntityAttribute(Integer dbData) {
        return ImpressoraProducao.get(dbData);
    }
}
