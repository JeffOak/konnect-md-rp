package com.skylab.service.mdrp;

import com.skylab.domain.exception.EntidadeNaoEncontradaException;
import com.skylab.domain.mdrp.model.ProdutoMdRp;
import com.skylab.domain.rp.model.material.Material;
import com.skylab.service.LogService;
import com.skylab.service.rp.MaterialService;
import com.skylab.service.md.api.MdService;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class SincronizadorProdutos {

    @Autowired
    private MaterialService materialService;
    @Autowired
    private ProdutoMdRpService produtoMdRpService;
    @Autowired
    private MdService mdService;

    //@Scheduled(fixedDelay = 2000)
    public void verificarProdutos() {
        LogService.log("Verificando produtos para sincronizar...");
        List<Material> materiais = materialService.getProdutos();
        materiais.forEach(material -> {
            try {
                ProdutoMdRp produtoMdRp = produtoMdRpService.buscarProduto(material.getId());

                LocalDateTime dataAlteracaoRp = new LocalDateTime(material.getUltimaAlteracao());
                LocalDateTime dataAlteracaoRpMd = new LocalDateTime(produtoMdRp.getUltimaAlteracao());

                if (dataAlteracaoRp.isAfter(dataAlteracaoRpMd)
                        | material.isStatus() != produtoMdRp.isAtivo()) {
                    LogService.log(String.format("Produto %d - %s foi alterado, sincronizando...",
                            material.getId(), material.getDescricao()));
                    mdService.alterarProduto(material, produtoMdRp);
                    produtoMdRp.setUltimaAlteracao(material.getUltimaAlteracao());
                    produtoMdRp.setAtivo(material.isStatus());
                    produtoMdRpService.salvar(produtoMdRp);
                    LogService.log(String.format("Produto %d - %s sincronizado com sucesso!",
                            material.getId(), material.getDescricao()));
                }
            } catch (EntidadeNaoEncontradaException ex) {
                LogService.log(String.format("Produto %d - %s ainda não incluído.",
                        material.getId(), material.getDescricao()));
                criarNovoProduto(material);
            } catch (Exception ex) {
                LogService.log(String.format("Erro ao incluir o produto %d - %s!",
                        material.getId(), material.getDescricao()));
                LogService.log(ex.getMessage());
                ex.printStackTrace();
            }
        });
    }

    private void criarNovoProduto(Material material) {
        LogService.log(String.format("Incluindo o produto %d - %s..",
                material.getId(), material.getDescricao()));
        Integer idMd = mdService.cadastrarProduto(material);
        ProdutoMdRp produtoMdRp = ProdutoMdRp.
                builder()
                .rpId(material.getId())
                .mdId(idMd)
                .ultimaAlteracao(new Timestamp(new java.util.Date().getTime()))
                .ativo(material.isStatus())
                .build();
        produtoMdRpService.salvar(produtoMdRp);
        LogService.log(String.format("Produto %d - %s incluído com sucesso!",
                material.getId(), material.getDescricao()));
    }
}
