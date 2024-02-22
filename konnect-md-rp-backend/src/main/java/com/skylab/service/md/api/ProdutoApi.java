package com.skylab.service.md.api;

import com.skylab.core.feign.MdApiConfig;
import com.skylab.domain.md.produto.ProdutoMd;
import com.skylab.service.md.api.model.produto.alteracaovalor.MdApiValorProdutoRequest;
import com.skylab.service.md.api.model.produto.ativardesativar.MdApiAtivarDesativarProdutoRequest;
import com.skylab.service.md.api.model.produto.cadastro.MdApiCadastroProdutoRequest;
import com.skylab.service.md.api.model.produto.cadastro.MdApiProdutoCadastroResponse;
import com.skylab.service.md.api.model.produto.listagem.MdApiListagemProdutoRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
@FeignClient(url = "NOT_USED", name = "produtos",
        configuration = MdApiConfig.class)
public interface ProdutoApi {

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<ProdutoMd> getProdutos(@RequestBody MdApiListagemProdutoRequest request);

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    MdApiProdutoCadastroResponse cadastrarProduto(@RequestBody MdApiCadastroProdutoRequest request);

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String alterarValor(@RequestBody MdApiValorProdutoRequest request);

    @RequestMapping(method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String ativarDesativar(@RequestBody MdApiAtivarDesativarProdutoRequest request);

}
