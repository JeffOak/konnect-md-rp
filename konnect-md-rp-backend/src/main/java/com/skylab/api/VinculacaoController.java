package com.skylab.api;

import com.skylab.api.model.FormaPagamentoMdDTO;
import com.skylab.domain.md.pedido.FormaPagamentoMd;
import com.skylab.domain.mdrp.model.FormaPagamentoMdRp;
import com.skylab.service.mdrp.FormaPagamentoMdRpService;
import com.skylab.service.mdrp.ProdutoMdRpService;
import com.skylab.service.mdrp.model.FormaPagamentoVinculadaMdRp;
import com.skylab.service.mdrp.model.ProdutoVinculadoMdRp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/vinculacao")
public class VinculacaoController {

    @Autowired
    private ProdutoMdRpService produtoMdRpService;
    @Autowired
    private FormaPagamentoMdRpService formaPagamentoMdRpService;

    @GetMapping("/produtos")
    public List<ProdutoVinculadoMdRp> getProdutoVinculacao() {
        return produtoMdRpService.getProdutosVinculacao();
    }

    @PostMapping(value = "/produtos")
    public void vincularProduto(@RequestBody ProdutoVinculadoMdRp produtoVinculadoMdRp) {
        produtoMdRpService.vincularProduto(produtoVinculadoMdRp);
    }

    @GetMapping("/pagamentos")
    public List<FormaPagamentoVinculadaMdRp> getPagamentosVinculacao() {
        return formaPagamentoMdRpService.getFormasVinculacao();
    }

    @PostMapping(value = "/pagamentos")
    public void vincularPagamento(@RequestBody FormaPagamentoVinculadaMdRp formaPagamentoVinculadaMdRp) {
        formaPagamentoMdRpService.vincularFormaPagamento(formaPagamentoVinculadaMdRp);
    }

    @GetMapping("/pagamentos/md")
    public List<FormaPagamentoMdDTO> getFormasPagamentoMd() {
        List<FormaPagamentoMdDTO> formas = new ArrayList<>();
        Arrays.asList(FormaPagamentoMd.values()).forEach(formaPagamentoMd -> {
            formas.add(FormaPagamentoMdDTO.builder()
                    .id(formaPagamentoMd.getId())
                    .descricao(formaPagamentoMd.getValue())
                    .build());
        });
        return formas;
    }

}
