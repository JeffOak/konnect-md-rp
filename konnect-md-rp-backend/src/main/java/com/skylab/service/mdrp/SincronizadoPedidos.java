package com.skylab.service.mdrp;

import com.skylab.domain.md.pedido.PedidoMd;
import com.skylab.service.LogService;
import com.skylab.service.md.api.MdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SincronizadoPedidos {

    @Autowired
    private PedidoMdRpService pedidoMdRpService;
    @Autowired
    private MdService mdService;

    @Scheduled(fixedDelay = 2000)
    //25026113
    public void verificarPedidos() {
        List<PedidoMd> pedidos = mdService.getPedidos();
        LogService.log("Verificando pedidos para sincronizar...");
        if (!pedidos.isEmpty()) {
            pedidos.forEach(pedido -> {
                        pedidoMdRpService.processarPedido(pedido);
                    });
        }
    }
}