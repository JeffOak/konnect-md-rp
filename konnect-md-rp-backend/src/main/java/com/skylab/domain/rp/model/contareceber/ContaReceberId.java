package com.skylab.domain.rp.model.contareceber;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ContaReceberId implements Serializable {

    @Column(name = "id_creceber")
    private int contaReceberId;
    @Column(name = "id_empresa")
    private int empresaId;
}
