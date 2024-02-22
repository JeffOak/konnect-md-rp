package com.skylab.service.mdrp;

import com.skylab.domain.exception.EntidadeNaoEncontradaException;
import com.skylab.domain.md.pedido.FormaPagamentoMd;
import com.skylab.domain.mdrp.exception.FormaPagamentoNaoEncontradaException;
import com.skylab.domain.mdrp.model.FormaPagamentoMdRp;
import com.skylab.domain.mdrp.repository.FormaPagamentoMdRpRepository;
import com.skylab.domain.rp.model.FormaPagamento;
import com.skylab.service.mdrp.model.FormaPagamentoVinculadaMdRp;
import com.skylab.service.rp.FormaPagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FormaPagamentoMdRpService {

    @Autowired
    private FormaPagamentoMdRpRepository formasPagamentosMdRp;
    @Autowired
    private FormaPagamentoService formaPagamentoService;

    public FormaPagamentoMdRp buscar(Integer id) {
        return formasPagamentosMdRp.findByidRp(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(""));
    }

    public FormaPagamentoMdRp buscarMdId(FormaPagamentoMd formaPagamentoMd) {
        return formasPagamentosMdRp.findByIdMd(Integer.toString(formaPagamentoMd.getId()))
                .orElseThrow(() -> new FormaPagamentoNaoEncontradaException(formaPagamentoMd));
    }

    public FormaPagamentoMdRp salvar(FormaPagamentoMdRp formaPagamento) {
        return formasPagamentosMdRp.save(formaPagamento);
    }

    public List<FormaPagamentoVinculadaMdRp> getFormasVinculacao() {
        List<FormaPagamento> formasPagamento = formaPagamentoService.formasPagamentos();
        List<FormaPagamentoVinculadaMdRp> formasVinculadas = new ArrayList<>();
        formasPagamento.forEach(formaPagamento -> {
            FormaPagamentoVinculadaMdRp formaPagamentoVinculadaMdRp = FormaPagamentoVinculadaMdRp
                    .builder().idRp(formaPagamento.getId())
                    .descricao(formaPagamento.getNome())
                    .build();
            try {
                FormaPagamentoMdRp formaPagamentoMdRp = buscar(formaPagamento.getId());
                formaPagamentoVinculadaMdRp.setIdMd(formaPagamentoMdRp.getIdMd() != null ?
                        formaPagamentoMdRp.getIdMd() : "");
            } catch (EntidadeNaoEncontradaException ex) {

            } finally {
                formasVinculadas.add(formaPagamentoVinculadaMdRp);
            }
        });
        return formasVinculadas;
    }

    public void vincularFormaPagamento(FormaPagamentoVinculadaMdRp formaPagamentoVinculadaMdRp) {
        String idMd = formaPagamentoVinculadaMdRp.getIdMd() == null
                || formaPagamentoVinculadaMdRp.getIdMd().isEmpty() ?
                null : formaPagamentoVinculadaMdRp.getIdMd();
        try {
            FormaPagamentoMdRp formaPagamentoMdRp = buscar(formaPagamentoVinculadaMdRp.getIdRp());
            formaPagamentoMdRp.setIdMd(idMd);
            salvar(formaPagamentoMdRp);
        } catch (EntidadeNaoEncontradaException ex) {
            FormaPagamentoMdRp formaPagamentoMdRp = FormaPagamentoMdRp.builder()
                    .idRp(formaPagamentoVinculadaMdRp.getIdRp())
                    .idMd(idMd)
                    .build();
            salvar(formaPagamentoMdRp);
        }
    }

}
