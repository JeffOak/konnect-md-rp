package com.skylab.service.rp;

import com.skylab.domain.rp.model.venda.Venda;
import com.skylab.domain.rp.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendas;

    public List<Venda> getVendas() {
        return vendas.findAll();
    }

    public Integer getProximoId() {
        return vendas.getMaxId() + 1;
    }

    public Venda salvar(Venda venda) {
        return vendas.save(venda);
    }

}
