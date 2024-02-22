package com.skylab.domain.mdrp.model.pedido.converter;

import com.skylab.domain.md.situacao.SituacaoPedidoMd;

import javax.persistence.AttributeConverter;

public class StatusMdConverter implements AttributeConverter<SituacaoPedidoMd, String> {
    @Override
    public String convertToDatabaseColumn(SituacaoPedidoMd attribute) {
        return attribute.getValue();
    }

    @Override
    public SituacaoPedidoMd convertToEntityAttribute(String dbData) {
        return SituacaoPedidoMd.get(dbData);
    }
}
