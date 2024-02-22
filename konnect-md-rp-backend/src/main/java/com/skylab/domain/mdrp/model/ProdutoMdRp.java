package com.skylab.domain.mdrp.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "PRODUTOS")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProdutoMdRp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "RP_ID")
    private Integer rpId;
    @Column(name = "MD_ID")
    private Integer mdId;
    @Column(name = "ULTIMA_ALTERACAO")
    private Timestamp ultimaAlteracao;
    @Column
    private boolean ativo;

}
