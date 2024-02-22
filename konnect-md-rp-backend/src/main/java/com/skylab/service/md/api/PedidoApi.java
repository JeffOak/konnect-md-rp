package com.skylab.service.md.api;

import com.skylab.core.feign.MdApiConfig;
import com.skylab.domain.md.pedido.PedidoMd;
import com.skylab.service.md.api.model.pedido.detalhe.MdApiDetalhePedidoRequest;
import com.skylab.service.md.api.model.pedido.listagem.MdApiListagemPedidoRequest;
import com.skylab.service.md.api.model.pedido.situacao.MdApiListagemSituacaoRequest;
import com.skylab.service.md.api.model.produto.alteracaovalor.MdApiValorProdutoRequest;
import com.skylab.service.md.api.model.produto.ativardesativar.MdApiAtivarDesativarProdutoRequest;
import com.skylab.service.md.api.model.produto.cadastro.MdApiCadastroProdutoRequest;
import com.skylab.service.md.api.model.produto.cadastro.MdApiProdutoCadastroResponse;
import com.skylab.service.md.api.model.produto.listagem.MdApiListagemProdutoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@FeignClient(url = "NOT_USED", name = "pedidos",
        configuration = MdApiConfig.class)
public interface PedidoApi {

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<PedidoMd> getPedidos(@RequestBody MdApiListagemPedidoRequest request);

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Object> getPedidos2(@RequestBody MdApiListagemPedidoRequest request);

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<Object> getSituacoes(@RequestBody MdApiListagemSituacaoRequest request);

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Object getPedido(@RequestBody MdApiDetalhePedidoRequest request);
}
