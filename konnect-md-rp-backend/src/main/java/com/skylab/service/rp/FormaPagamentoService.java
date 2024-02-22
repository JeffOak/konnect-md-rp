package com.skylab.service.rp;

import com.skylab.domain.exception.EntidadeNaoEncontradaException;
import com.skylab.domain.rp.model.FormaPagamento;
import com.skylab.domain.rp.repository.FormaPagamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository pagamentos;

    public FormaPagamento buscar(Integer id) {
        return pagamentos.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(""));
    }

    public List<FormaPagamento> formasPagamentos() {
        return pagamentos.findAll();
    }

}
