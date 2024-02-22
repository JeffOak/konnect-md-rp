package com.skylab.domain.rp.model;

import com.skylab.FormatValues;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.MessageFormat;

@Entity
@Table(name = "EMPRESAS")
@Getter
@Setter
public class Loja {


    @Id
    @Column(name = "emp_001")
    private Integer id;
    @Column(name = "emp_002")
    private String nomeRazaoSocial;
    @Column(name = "emp_003")
    private String nomeFantasia;
    @Column(name = "emp_004")
    private String cpfCnpj;

    @Column(name = "cep_004")
    private String endereco;
    @Column(name = "emp_007")
    private String numero;
    @Column(name = "emp_008")
    private String complemento;
    @Column(name = "cep_003")
    private String bairro;
    @Column(name = "cep_002")
    private String cep;
    @Column(name = "emp_013")
    private String telefone;
    @ManyToOne
    @JoinColumn(name = "cid_001")
    private Cidade cidade;

    @Override
    public String toString() {
        String cabecalho = "";
        cabecalho = cabecalho.concat(nomeRazaoSocial) + "\n";
        cabecalho = cabecalho.concat(nomeFantasia) + "\n";
        cabecalho = cabecalho.concat(FormatValues.mascaraCpfCnpj(cpfCnpj)) + "\n";
        cabecalho = cabecalho.concat(getEndereco()) + "\n";
        cabecalho = cabecalho.concat(FormatValues.mascaraTelefone(telefone));
        return cabecalho;
    }

    public String getEndereco() {
        return MessageFormat.format("{0} {1} {2} - {3}\n{4} / {5}",
                verifyStringNullable(endereco),
                verifyStringNullable(numero),
                verifyStringNullable(complemento),
                verifyStringNullable(bairro),
                verifyStringNullable(cidade.getNome()),
                verifyStringNullable(cidade.getEstado().getSigla()));
    }

    private String verifyStringNullable(String value) {
        return value != null && !value.isEmpty() ? value : "";
    }
}
