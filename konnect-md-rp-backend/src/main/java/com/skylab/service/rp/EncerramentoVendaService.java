package com.skylab.service.rp;

import com.skylab.domain.rp.repository.EncerraVendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EncerramentoVendaService {

    @Autowired
    private EncerraVendaRepository encerraVendaRepository;

    public Integer getProximoId() {
        return encerraVendaRepository.getMaxId() + 1;
    }
}
