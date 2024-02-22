package com.skylab.core.feign;

import com.skylab.service.mdrp.ConfiguracaoMdRpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmpresaConfiguration {

    @Autowired
    private ConfiguracaoMdRpService configuracaoMdRpService;

    @Bean
    public int getEmpresaId() {
        return Integer.parseInt(configuracaoMdRpService.getConfiguracao().getCodigoEmpresa());
    }
}
