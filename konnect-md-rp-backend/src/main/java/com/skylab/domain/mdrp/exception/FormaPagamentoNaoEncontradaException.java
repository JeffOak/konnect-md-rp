package com.skylab.domain.mdrp.exception;

import com.skylab.domain.exception.EntidadeNaoEncontradaException;
import com.skylab.domain.md.pedido.FormaPagamentoMd;

public class FormaPagamentoNaoEncontradaException extends EntidadeNaoEncontradaException {

    public FormaPagamentoNaoEncontradaException(FormaPagamentoMd formaPagamento) {
        super(String.format("Forma de pagamento '%s' não vinculada!", formaPagamento.getValue()));
    }
}
