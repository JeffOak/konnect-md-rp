package com.skylab.service.md.api;

import com.skylab.core.feign.MdApiConfig;
import com.skylab.domain.md.estabelecimento.EstabelecimentoMd;
import com.skylab.domain.md.pedido.PedidoMd;
import com.skylab.service.md.api.model.estabelecimento.MdEstabelecimentoRequest;
import com.skylab.service.md.api.model.pedido.detalhe.MdApiDetalhePedidoRequest;
import com.skylab.service.md.api.model.pedido.listagem.MdApiListagemPedidoRequest;
import com.skylab.service.md.api.model.pedido.situacao.MdApiListagemSituacaoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
@FeignClient(url = "NOT_USED", name = "estabelecimento",
        configuration = MdApiConfig.class)
public interface EstabelecimentoApi {

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    EstabelecimentoMd getEstabelecimento(@RequestBody MdEstabelecimentoRequest request);

}
