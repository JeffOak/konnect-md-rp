package com.skylab.service.md.api;

import com.skylab.core.feign.EmpresaConfiguration;
import com.skylab.domain.exception.EntidadeNaoEncontradaException;
import com.skylab.domain.md.estabelecimento.EstabelecimentoMd;
import com.skylab.domain.md.pedido.ItemPedidoMd;
import com.skylab.domain.md.pedido.PedidoMd;
import com.skylab.domain.mdrp.model.ProdutoMdRp;
import com.skylab.domain.rp.model.material.Material;
import com.skylab.domain.md.produto.ProdutoMd;
import com.skylab.service.LogService;
import com.skylab.service.md.api.model.estabelecimento.MdEstabelecimentoRequest;
import com.skylab.service.md.api.model.pedido.detalhe.MdApiDetalhePedidoRequest;
import com.skylab.service.md.api.model.pedido.listagem.MdApiListagemPedidoRequest;
import com.skylab.service.md.api.model.pedido.situacao.MdApiListagemSituacaoRequest;
import com.skylab.service.md.api.model.produto.alteracaovalor.MdApiValorProdutoRequest;
import com.skylab.service.md.api.model.produto.alteracaovalor.MdProdutoAlterarValor;
import com.skylab.service.md.api.model.produto.ativardesativar.MdApiAtivarDesativarProdutoRequest;
import com.skylab.service.md.api.model.produto.ativardesativar.MdProdutoAtivarDesativar;
import com.skylab.service.md.api.model.produto.cadastro.MdApiCadastroProdutoRequest;
import com.skylab.service.md.api.model.produto.cadastro.MdApiProdutoCadastroResponse;
import com.skylab.service.md.api.model.produto.listagem.MdApiListagemProdutoRequest;
import com.skylab.service.mdrp.ProdutoMdRpService;
import com.skylab.service.rp.MaterialService;
import feign.codec.DecodeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MdService {

    @Autowired
    private ProdutoApi produtoApi;
    @Autowired
    private PedidoApi pedidoApi;
    @Autowired
    private EstabelecimentoApi estabelecimentoApi;
    @Autowired
    private MaterialService materialService;
    @Autowired
    private ProdutoMdRpService produtoMdRpService;
    @Autowired
    private EmpresaConfiguration empresaConfiguration;

    public List<ProdutoMd> getProdutos() {
        MdApiListagemProdutoRequest request = MdApiListagemProdutoRequest.builder()
                .sandBox(false)
                .idEmpresaIntegrada(73)
                .idOrigem(11)
                .idRestaurante(empresaConfiguration.getEmpresaId())
                .build();
        return produtoApi.getProdutos(request);
    }

    public Integer cadastrarProduto(Material material) {
        List<ProdutoMd> produtos = new ArrayList<>();

        produtos.add(ProdutoMd.builder()
                .nome(material.getDescricao())
                .descricao("")
                .quantidadeEstoque(BigDecimal.ONE)
                .categoria("SOPA")
                .ativo(1)
                .valor(material.getValor().toString().replace(".", ","))
                .build());
        MdApiCadastroProdutoRequest request = MdApiCadastroProdutoRequest.builder()
                .sandBox(false)
                .idEmpresaIntegrada(73)
                .idOrigem(11)
                .idRestaurante(empresaConfiguration.getEmpresaId())
                .produtos(produtos)
                .build();

        MdApiProdutoCadastroResponse response = produtoApi.cadastrarProduto(request);
        return response.getCodigosCardapio().get(0);
    }

    public void alterarProduto(Material material, ProdutoMdRp produtoMdRp) {
        List<MdProdutoAlterarValor> produtosAlterarValor = new ArrayList<>();
        List<MdProdutoAtivarDesativar> produtosAtivarDesativar = new ArrayList<>();
        produtosAlterarValor.add(MdProdutoAlterarValor.builder()
                .idCardapio(produtoMdRp.getMdId())
                .valor(material.getValor().toString().replace(".", ","))
                .build());

        produtosAtivarDesativar.add(MdProdutoAtivarDesativar.builder()
                .idCardapio(produtoMdRp.getMdId())
                .ativo(material.isStatus() ? 1 : 0)
                .build());

        MdApiValorProdutoRequest requestValor = MdApiValorProdutoRequest.builder()
                .sandBox(false)
                .idEmpresaIntegrada(73)
                .idOrigem(11)
                .idRestaurante(empresaConfiguration.getEmpresaId())
                .produtos(produtosAlterarValor)
                .build();

        MdApiAtivarDesativarProdutoRequest requestAtivarDesativar
                = MdApiAtivarDesativarProdutoRequest.builder()
                .sandBox(false)
                .idEmpresaIntegrada(73)
                .idOrigem(11)
                .idRestaurante(empresaConfiguration.getEmpresaId())
                .produtos(produtosAtivarDesativar)
                .build();

        produtoApi.alterarValor(requestValor);
        produtoApi.ativarDesativar(requestAtivarDesativar);
    }

    public List<Object> getPedidos2() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            MdApiListagemPedidoRequest request = MdApiListagemPedidoRequest.builder()
                    .sandBox(false)
                    .idEmpresaIntegrada(73)
                    .idOrigem(11)
                    .idRestaurante(empresaConfiguration.getEmpresaId())
                    .dataInicio(dateFormat.format(yesterday()))
                    .dataFim(dateFormat.format(new Date()))
                    //.idSituacao(Collections.singletonList(SituacaoPedidoMd.VERIFICANDO))
                    .build();
            return pedidoApi.getPedidos2(request);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<PedidoMd> getPedidos() {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            MdApiListagemPedidoRequest request = MdApiListagemPedidoRequest.builder()
                    .sandBox(false)
                    .idEmpresaIntegrada(73)
                    .idOrigem(11)
                    .idRestaurante(empresaConfiguration.getEmpresaId())
                    .dataInicio(dateFormat.format(yesterday()))
                    .dataFim(dateFormat.format(new Date()))
                    //.idSituacao(Collections.singletonList(SituacaoPedidoMd.VERIFICANDO))
                    .build();
            return pedidoApi.getPedidos(request);
        } catch (DecodeException decodeException) {
            return new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Object getPedido(int idPedido) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            MdApiDetalhePedidoRequest request = MdApiDetalhePedidoRequest.builder()
                    .sandBox(false)
                    .idEmpresaIntegrada(73)
                    .idOrigem(11)
                    .idRestaurante(empresaConfiguration.getEmpresaId())
                    .idPedido(Collections.singletonList(idPedido))
                    .dataInicio(dateFormat.format(dateFormat.parse("2024-02-01 00:00:00")))
                    .dataFim(dateFormat.format(new Date()))
                    .build();
            return pedidoApi.getPedido(request);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ArrayList<>();
        }
    }

    public List<Object> getSituacoes() {
        MdApiListagemSituacaoRequest request = MdApiListagemSituacaoRequest.builder()
                .sandBox(false)
                .idEmpresaIntegrada(73)
                .idOrigem(11)
                .idRestaurante(empresaConfiguration.getEmpresaId())
                .build();
        return pedidoApi.getSituacoes(request);
    }


    public Material getProduto(ItemPedidoMd item) {
        try {
            List<ProdutoMd> produtos = getProdutos();

            ProdutoMd produtoMdEncontrado = produtos.stream().filter(produtoMd ->
                    produtoMd.getCategoria().equals(item.getCategoria())
                            && produtoMd.getNome().equals(item.getNome())
            ).findFirst().get();

            ProdutoMdRp produtoMdRp = produtoMdRpService
                    .buscarProduto(produtoMdEncontrado.getId().intValue());
            Material produtoRp = materialService.buscarProduto(produtoMdRp.getRpId());
            return produtoRp;
        } catch (EntidadeNaoEncontradaException ex) {
            LogService.log(String.format("Produto %d - %s não encontrado!",
                    1, ""));
            throw ex;
        }
    }

    public EstabelecimentoMd getEstabelecimento() {
        return estabelecimentoApi.getEstabelecimento(
                MdEstabelecimentoRequest.builder()
                        .sandBox(false)
                        .idEmpresaIntegrada(73)
                        .idOrigem(11)
                        .idRestaurante(empresaConfiguration.getEmpresaId())
                        .build());

    }

    private Date yesterday() {
        final Calendar cal = Calendar.getInstance();
        return cal.getTime();
    }
}