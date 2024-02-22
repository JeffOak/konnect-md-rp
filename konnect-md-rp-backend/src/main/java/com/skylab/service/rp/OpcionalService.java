package com.skylab.service.rp;

import com.skylab.domain.rp.model.material.Opcional;
import com.skylab.domain.rp.repository.OpcionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpcionalService {

    @Autowired
    private OpcionalRepository opcionais;

    public Opcional salvar(Opcional opcional) {
        return opcionais.save(opcional);
    }

    public int getProximoId() {
        return opcionais .getMaxId() + 1;
    }
}
