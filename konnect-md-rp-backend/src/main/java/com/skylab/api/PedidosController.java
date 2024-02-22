package com.skylab.api;

import com.skylab.domain.md.pedido.PedidoMd;
import com.skylab.domain.md.produto.ProdutoMd;
import com.skylab.domain.mdrp.model.pedido.PedidoMdRp;
import com.skylab.domain.rp.model.material.Material;
import com.skylab.domain.rp.model.venda.Venda;
import com.skylab.service.mdrp.PedidoMdRpService;
import com.skylab.service.rp.MaterialService;
import com.skylab.service.rp.VendaService;
import com.skylab.service.md.api.MdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private VendaService vendaService;
    @Autowired
    private MdService mdService;
    @Autowired
    private PedidoMdRpService pedidoMdRpService;


    @GetMapping("/rp/vendas")
    public List<Venda> getVendas() {
        return vendaService.getVendas();
    }

    @GetMapping("/md/pedidos")
    public List<PedidoMd> getPedidos() {
        return mdService.getPedidos();
    }

    @GetMapping("/md_rp/pedidos_erros")
    public List<PedidoMdRp> getPedidosComErros() {
        return pedidoMdRpService.pedidosComErros();
    }

    @GetMapping("/md_rp/reprocessar/{idPedido}")
    public void getPedidosComErros(@PathVariable Integer idPedido) {
        pedidoMdRpService.deletarPedido(idPedido);
    }

    @GetMapping("/md/pedidos2")
    public List<Object> getPedidos2() {
        return mdService.getPedidos2();
    }

    @GetMapping("/md/pedidos/{idPedido}")
    public Object getPedidos(@PathVariable int idPedido) {
        return mdService.getPedido(idPedido);
    }

    @GetMapping("/md/situacoes")
    public List<Object> getSituacoes() {
        return mdService.getSituacoes();
    }
}
