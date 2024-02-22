package com.skylab.domain.rp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "estados")
@Getter
@Setter
public class Estado {

    @Id
    @Column(name = "est_001")
    private Integer id;
    @Column(name = "est_002")
    private String nome;
    @Column(name = "est_003")
    private String sigla;
}
