package com.skylab.core.feign;

import com.skylab.domain.mdrp.model.ConfiguracaoMdRp;
import com.skylab.service.mdrp.ConfiguracaoMdRpService;
import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

public class MdApiConfig {

    @Autowired
    private ConfiguracaoMdRpService configuracaoMdRpService;

    @Bean
    public RequestInterceptor urlInterceptor() {
        return template -> template
                .target("https://maisdeliveryapp.com.br/oapi/v2/controller/operacao_oapi_v2_publica.php");
    }

    @Bean
    public RequestInterceptor requestInterceptor() {
        ConfiguracaoMdRp configuracaoMdRp = configuracaoMdRpService.getConfiguracao();
        return template -> {
            template
                    .header("Accesstoken",
                            " " + configuracaoMdRp.getToken())
                    .header("Authorization",
                            "Basic bWFpc2RlbGl2ZXJ5YXBpaXByb2Q6djZXbXNFSHFHNDNuNFF6bXo0Nlo=")
                    .header("user-agent",
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        };
    }
}