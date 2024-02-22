package com.skylab.service.rp;

import com.skylab.domain.rp.model.cliente.Cliente;
import com.skylab.domain.rp.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clientes;

    public Integer getProximoId() {
        return clientes.getMaxId() + 1;
    }

    public Cliente salvar(Cliente cliente) {
        return clientes.save(cliente);
    }

}
