package com.skylab.service.mdrp;

import com.skylab.domain.md.pedido.AdicionalPedidoMd;
import com.skylab.domain.md.pedido.ItemPedidoMd;
import com.skylab.domain.md.pedido.PedidoMd;
import com.skylab.domain.md.produto.ProdutoMd;
import com.skylab.domain.md.situacao.SituacaoPedidoMd;
import com.skylab.domain.mdrp.exception.FormaPagamentoNaoEncontradaException;
import com.skylab.domain.mdrp.exception.PedidoNaoEncontradoException;
import com.skylab.domain.mdrp.exception.ProdutoNaoEncontradoException;
import com.skylab.domain.mdrp.model.FormaPagamentoMdRp;
import com.skylab.domain.mdrp.model.pedido.PedidoMdRp;
import com.skylab.domain.mdrp.model.ProdutoMdRp;
import com.skylab.domain.mdrp.repository.PedidoMdRpRepository;
import com.skylab.domain.rp.model.FormaPagamento;
import com.skylab.domain.rp.model.material.Material;
import com.skylab.domain.rp.model.cliente.Cliente;
import com.skylab.domain.rp.model.material.MaterialOpcional;
import com.skylab.domain.rp.model.material.MaterialOpcionalId;
import com.skylab.domain.rp.model.material.Opcional;
import com.skylab.domain.rp.model.venda.*;
import com.skylab.service.LogService;
import com.skylab.service.md.api.MdService;
import com.skylab.service.rp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoMdRpService {

    @Autowired
    private MaterialService materialService;
    @Autowired
    private ProdutoMdRpService produtoMdRpService;
    @Autowired
    private MdService mdService;
    @Autowired
    private FormaPagamentoService formaPagamentoService;
    @Autowired
    private FormaPagamentoMdRpService formaPagamentoMdRpService;
    @Autowired
    private VendaService vendaService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private PedidoMdRpRepository pedidosMdRp;
    @Autowired
    private VendaPagamentoAntecipadoService vendaPagamentoAntecipadoService;
    @Autowired
    private OpcionalService opcionalService;
    @Autowired
    private MaterialOpcionalService materialOpcionalService;

    public PedidoMdRp getPedidoMdId(String mdId) {
        return pedidosMdRp.findByMdId(mdId)
                .orElseThrow(PedidoNaoEncontradoException::new);
    }

    public void processarPedido(PedidoMd pedidoMd) {
        try {
            getPedidoMdId(pedidoMd.getIdPedido());
        } catch (PedidoNaoEncontradoException ex) {
            LogService.log(String.format("Sincronizando pedido %s...",
                    pedidoMd.getIdPedido()));
            criarPedido(pedidoMd);
        }
    }

    private void criarPedido(PedidoMd pedidoMd) {
        try {
            FormaPagamentoMdRp formaPagamentoMdRp = formaPagamentoMdRpService
                    .buscarMdId(pedidoMd.getFormaPagamento());

            FormaPagamento formaPagamento = formaPagamentoService
                    .buscar(formaPagamentoMdRp.getIdRp());

            BigDecimal taxaEntrega = new BigDecimal(pedidoMd.getTaxaEntrega().replace(",", "."));
            BigDecimal total = new BigDecimal(pedidoMd.getTotal().replace(",", "."));

            DateFormat dateFormat = new SimpleDateFormat("DD/mm/yyyy HH:mm:ss");
            Integer idVenda = vendaService.getProximoId();

            Timestamp data = new Timestamp(dateFormat.parse(pedidoMd.getData()).getTime());

            Venda venda = Venda.builder()
                    .vendaId(idVenda)
                    .idSecundario(idVenda)
                    .data(data)
                    .status(StatusVenda.EM_PROCESSO)
                    .desconto(BigDecimal.ZERO)
                    .acrescimo(taxaEntrega)
                    .total(total)
                    .terminalAbertura("MAIS DELIVERY")
                    .nomeCliente(pedidoMd.getNomeCliente())
                    .cliente(cadastrarCliente(pedidoMd))
                    .taxaEntrega(taxaEntrega.compareTo(BigDecimal.ZERO) > 0)
                    .tipoFiscal(null)
                    .xmlCfe(null)
                    .origemVenda(OrigemVenda.DELIVERY)
                    .troco(BigDecimal.ZERO)
                    .observacao(String.format("%s ---- %s",
                            pedidoMd.getObservacao(),
                            "MAIS DELIVERY - PEDIDO #" + pedidoMd.getIdPedido()))
                    .vendaPagamentoAntecipado(Collections.singletonList(
                            VendaPagamentoAntecipado
                                    .builder()
                                    .id(VendaPagAntecipadoId.builder()
                                            .id(vendaPagamentoAntecipadoService.getProximoId())
                                            .vendaId(idVenda)
                                            .build())
                                    .data(data)
                                    .valor(total)
                                    .idFormaPagamento(formaPagamento.getId())
                                    .build()
                    ))
                    .painelSenha(Long.parseLong(pedidoMd.getIdPedido()))
                    .build();
            List<ItemVenda> itens = buscarProdutoVinculado(pedidoMd, venda);
            venda.setItens(itens);

            vendaService.salvar(venda);
            salvarPedidoMdRp(pedidoMd, venda);
        } catch (ProdutoNaoEncontradoException | FormaPagamentoNaoEncontradaException
                entidadesNaoEncontrada) {
            LogService.log(entidadesNaoEncontrada.getMessage());
            PedidoMdRp pedidoComErro = PedidoMdRp.builder()
                    .erro(true)
                    .statusMd(SituacaoPedidoMd.VERIFICANDO)
                    .statusRp(StatusVenda.EM_PROCESSO)
                    .mdId(pedidoMd.getIdPedido())
                    .descricao_erro(entidadesNaoEncontrada.getMessage())
                    .build();
            pedidosMdRp.save(pedidoComErro);
        } catch (Exception exception) {
            LogService.log("Erro ao processar o pedido #%d:",
                    pedidoMd.getIdPedido());
            LogService.log(exception.getLocalizedMessage());
            exception.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            exception.printStackTrace(pw);
            PedidoMdRp pedidoComErro = PedidoMdRp.builder()
                    .mdId(pedidoMd.getIdPedido())
                    .erro(true)
                    .statusMd(SituacaoPedidoMd.VERIFICANDO)
                    .statusRp(StatusVenda.EM_PROCESSO)
                    .descricao_erro(pw.toString())
                    .build();
            pedidosMdRp.save(pedidoComErro);
        }
    }

    private List<ItemVenda> buscarProdutoVinculado(PedidoMd pedido, Venda venda) {
        List<ProdutoMd> produtos = mdService.getProdutos();
        List<ItemVenda> itens = new ArrayList<>();
        List<ItemVendaOpcional> opcionais = new ArrayList<>();
        int idItem = 1;
        for (ItemPedidoMd item : pedido.getItens()) {
            ProdutoMd produtoMdEncontrado = produtos.stream().filter(produtoMd ->
                            produtoMd.getCategoria().equals(item.getCategoria())
                                    && produtoMd.getNome().equals(item.getNome()))
                    .findFirst().get();

            ItemVendaId itemVendaId = ItemVendaId.builder()
                    .id(idItem)
                    .vendaId(venda.getVendaId())
                    .build();

            ProdutoMdRp produtoMdRp = produtoMdRpService
                    .buscarProdutoMdId(produtoMdEncontrado);
            Material produtoRp = materialService.buscarProduto(produtoMdRp.getRpId());
            ItemVenda itemVenda = ItemVenda.builder()
                    .itemVendaId(itemVendaId)
                    .materialId(produtoRp.getId())
                    .observacao(item.getObservacao())
                    .situacao(4)
                    .quantidade(new BigDecimal(item.getQuantidade().replace(",", ".")))
                    .valor(new BigDecimal(item.getValor().replace(",", ".")))
                    .total(new BigDecimal(item.getTotal().replace(",", ".")))
                    .impressora1(produtoRp.getImpressora1())
                    .impressora2(produtoRp.getImpressora2())
                    .build();

            for (AdicionalPedidoMd adicional : item.getAdicionais()) {
                Optional<Opcional> opcionalEncontrado = produtoRp
                        .getOpcionais().stream().filter(opc ->
                                opc.getDescricao().equals(adicional.getNome()))
                        .findFirst();

                BigDecimal valorAdicional = new
                        BigDecimal(adicional.getValor().replace(",", "."));

                if (opcionalEncontrado.isPresent()) {
                    ItemVendaOpcional itemVendaOpcional = ItemVendaOpcional
                            .builder()
                            .id(ItemVendaOpcionalId.builder()
                                    .idVendaItem(idItem)
                                    .idOpcional(opcionalEncontrado.get().getId())
                                    .idVenda(venda.getVendaId())
                                    .build())
                            .gratis(false)
                            .valor(valorAdicional)
                            .build();
                    opcionais.add(itemVendaOpcional);
                } else {
                    Opcional opcional = Opcional
                            .builder()
                            .id(opcionalService.getProximoId())
                            .descricao(adicional.getNome())
                            .valor(valorAdicional)
                            .build();
                    opcionalService.salvar(opcional);

                    MaterialOpcional materialOpcional = MaterialOpcional
                            .builder()
                            .id(MaterialOpcionalId
                                    .builder()
                                    .idOpcional(opcional.getId())
                                    .materialId(produtoRp.getId())
                                    .build())
                            .build();

                    materialOpcionalService.salvar(materialOpcional);

                    ItemVendaOpcional itemVendaOpcional = ItemVendaOpcional
                            .builder()
                            .id(ItemVendaOpcionalId.builder()
                                    .idVendaItem(idItem)
                                    .idOpcional(opcional.getId())
                                    .idVenda(venda.getVendaId())
                                    .build())
                            .gratis(false)
                            .valor(valorAdicional)
                            .build();
                    opcionais.add(itemVendaOpcional);
                    produtoRp.getOpcionais().add(opcional);
                    materialService.salvar(produtoRp);
                }
            }
            itemVenda.setOpcionais(opcionais);
            itens.add(itemVenda);
            idItem++;
        }
        return itens;
    }

    private Cliente cadastrarCliente(PedidoMd pedidoMd) {
        String enderecoMd = pedidoMd.getEndereco();
        StringBuilder endereco = new StringBuilder();

        if (enderecoMd.length() > 125) {
            endereco.append(enderecoMd.substring(0, 124));
            endereco.append(enderecoMd.substring(125, enderecoMd.length() - 1));
        } else {
            endereco.append(enderecoMd);
        }
        Cliente cliente = Cliente.builder()
                .id(clienteService.getProximoId())
                .nomeRazaoSocial(pedidoMd.getNomeCliente())
                .endereco(endereco.toString())
                .build();
        return clienteService.salvar(cliente);
    }

    public PedidoMdRp salvarPedidoMdRp(PedidoMd pedidoMd, Venda pedidoRp) {
        return pedidosMdRp.save(PedidoMdRp
                .builder()
                .rpId(pedidoRp.getVendaId())
                .mdId(pedidoMd.getIdPedido())
                .statusMd(pedidoMd.getSituacao())
                .statusRp(pedidoRp.getStatus())
                .mensagemEnviada(false)
                .build());
    }

    public List<PedidoMdRp> pedidosComErros() {
        return pedidosMdRp.findByErro(true);
    }

    public void deletarPedido(Integer id) {
        pedidosMdRp.deleteById(id);
    }

}
