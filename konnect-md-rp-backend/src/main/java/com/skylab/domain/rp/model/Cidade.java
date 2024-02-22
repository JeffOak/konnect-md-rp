package com.skylab.domain.rp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "CIDADES")
@Getter
@Setter
public class Cidade {

    @Id
    @Column(name = "cid_001")
    private Integer id;
    @Column(name = "cid_002")
    private String nome;
    @ManyToOne
    @JoinColumn(name = "est_001")
    private Estado estado;
}
