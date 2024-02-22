package com.skylab.domain.rp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "formapgto")
@Getter
@Setter
public class FormaPagamento {

    @Id
    @Column(name = "for_001")
    private Integer id;
    @Column(name = "for_002")
    private String nome;

}
