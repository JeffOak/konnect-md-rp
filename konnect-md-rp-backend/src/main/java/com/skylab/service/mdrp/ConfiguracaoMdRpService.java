package com.skylab.service.mdrp;

import com.skylab.domain.mdrp.model.ConfiguracaoMdRp;
import com.skylab.domain.mdrp.repository.ConfiguracaoMdRpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfiguracaoMdRpService {

    @Autowired
    private ConfiguracaoMdRpRepository configuracaoMdRpRepository;


    public ConfiguracaoMdRp getConfiguracao() {
        return configuracaoMdRpRepository.findById(1).get();
    }

}
