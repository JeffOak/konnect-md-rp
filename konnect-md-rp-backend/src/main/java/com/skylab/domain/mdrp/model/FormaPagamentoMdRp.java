package com.skylab.domain.mdrp.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "FORMAS_PAGAMENTO")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FormaPagamentoMdRp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "RP_ID")
    private Integer idRp;
    @Column(name = "MD_ID")
    private String idMd;

}
