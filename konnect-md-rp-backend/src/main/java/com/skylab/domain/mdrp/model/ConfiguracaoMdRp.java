package com.skylab.domain.mdrp.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CONFIGURACAO")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfiguracaoMdRp {

    @Id
    private Integer id;
    @Column(name = "CODIGO_EMPRESA")
    private String codigoEmpresa;
    private String token;
    @Column(name = "INTERVALO_SINCRONIZACAO")
    private Integer intervaloSincronizacao;

}
