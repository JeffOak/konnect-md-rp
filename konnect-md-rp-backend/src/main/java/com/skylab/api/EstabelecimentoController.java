package com.skylab.api;

import com.skylab.domain.md.estabelecimento.EstabelecimentoMd;
import com.skylab.service.md.api.MdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EstabelecimentoController {

    @Autowired
    private MdService mdService;

    @GetMapping("/")
    public EstabelecimentoMd getEstabelecimento() {
        return mdService.getEstabelecimento();
    }

}
