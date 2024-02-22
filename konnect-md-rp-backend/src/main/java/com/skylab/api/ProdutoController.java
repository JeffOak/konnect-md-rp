package com.skylab.api;

import com.skylab.domain.md.pedido.PedidoMd;
import com.skylab.domain.md.produto.ProdutoMd;
import com.skylab.domain.rp.model.material.Material;
import com.skylab.domain.rp.model.venda.Venda;
import com.skylab.service.md.api.MdService;
import com.skylab.service.rp.MaterialService;
import com.skylab.service.rp.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private MaterialService materialService;
    @Autowired
    private MdService mdService;

    @GetMapping("/rp")
    public List<Material> getProdutos() {
        return materialService.getProdutos();
    }


    @GetMapping("/md")
    public List<ProdutoMd> getMdProdutos() {
        return mdService.getProdutos();
    }

}
