package com.skylab.service.rp;

import com.skylab.domain.rp.model.material.MaterialOpcional;
import com.skylab.domain.rp.repository.MaterialOpcionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterialOpcionalService {

    @Autowired
    private MaterialOpcionalRepository materialOpcionalRepository;

    public void salvar(MaterialOpcional materialOpcional) {
        materialOpcionalRepository.save(materialOpcional);
    }

}
