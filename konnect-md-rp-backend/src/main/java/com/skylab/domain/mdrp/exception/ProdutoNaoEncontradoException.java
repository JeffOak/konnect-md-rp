package com.skylab.domain.mdrp.exception;

import com.skylab.domain.exception.EntidadeNaoEncontradaException;
import com.skylab.domain.md.produto.ProdutoMd;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

    public ProdutoNaoEncontradoException(ProdutoMd produtoMd) {
        super(String.format("Produto '%d - %s' não vinculado!",
                produtoMd.getId(),
                produtoMd.getNome()));

    }
}
