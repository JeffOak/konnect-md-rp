package com.skylab.service.mdrp;

import com.skylab.domain.exception.EntidadeNaoEncontradaException;
import com.skylab.domain.md.produto.ProdutoMd;
import com.skylab.domain.mdrp.exception.ProdutoNaoEncontradoException;
import com.skylab.domain.mdrp.model.ProdutoMdRp;
import com.skylab.domain.mdrp.repository.ProdutoMdRpRepository;
import com.skylab.domain.rp.model.material.Material;
import com.skylab.service.mdrp.model.ProdutoVinculadoMdRp;
import com.skylab.service.rp.MaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProdutoMdRpService {

    @Autowired
    private ProdutoMdRpRepository produtos;
    @Autowired
    private MaterialService materialService;

    public ProdutoMdRp buscarProduto(Integer id) {
        return produtos.findByRpId(id).orElseThrow(() -> new EntidadeNaoEncontradaException(""));
    }

    public ProdutoMdRp buscarProdutoMdId(ProdutoMd produtoMd) {
        return produtos.findByMdId(produtoMd.getId().intValue()).orElseThrow(() ->
                new ProdutoNaoEncontradoException(produtoMd));
    }

    public ProdutoMdRp salvar(ProdutoMdRp produtoMdRp) {
        return produtos.save(produtoMdRp);
    }

    public List<ProdutoVinculadoMdRp> getProdutosVinculacao() {
        List<Material> materiais = materialService.getProdutos();
        List<ProdutoVinculadoMdRp> produtosVinculadoMdRp = new ArrayList<>();
        materiais.forEach(material -> {
            ProdutoVinculadoMdRp produtoVinculacao = ProdutoVinculadoMdRp
                    .builder().idRp(material.getId())
                    .descricao(material.getDescricao())
                    .build();
            try {
                ProdutoMdRp produtoMdRp = buscarProduto(material.getId());
                produtoVinculacao.setIdMd(produtoMdRp.getMdId() != null ?
                        produtoMdRp.getMdId().toString() : "");
            } catch (EntidadeNaoEncontradaException ex) {

            } finally {
                produtosVinculadoMdRp.add(produtoVinculacao);
            }
        });
        return produtosVinculadoMdRp;
    }

    public void vincularProduto(ProdutoVinculadoMdRp produtoVinculadoMdRp) {
        Integer idMd = produtoVinculadoMdRp.getIdMd().equals("") ?
                null : Integer.parseInt(produtoVinculadoMdRp.getIdMd());
        try {
            ProdutoMdRp produtoMdRp = buscarProduto(produtoVinculadoMdRp.getIdRp());
            produtoMdRp.setMdId(idMd);
            produtoMdRp.setUltimaAlteracao(new Timestamp(new Date().getTime()));
            salvar(produtoMdRp);
        } catch (EntidadeNaoEncontradaException ex) {
            ProdutoMdRp produtoMdRp = ProdutoMdRp.builder()
                    .rpId(produtoVinculadoMdRp.getIdRp())
                    .mdId(idMd)
                    .ativo(true)
                    .ultimaAlteracao(new Timestamp(new Date().getTime()))
                    .build();
            salvar(produtoMdRp);
        }
    }
}
