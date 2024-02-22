package com.skylab.service.rp;

import com.skylab.domain.rp.repository.VendaPagamentoAntecipadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VendaPagamentoAntecipadoService {


    @Autowired
    private VendaPagamentoAntecipadoRepository vendaPagamentoAntecipadoRepository;

    public int getProximoId() {
        return vendaPagamentoAntecipadoRepository.getMaxId() + 1;
    }

}
