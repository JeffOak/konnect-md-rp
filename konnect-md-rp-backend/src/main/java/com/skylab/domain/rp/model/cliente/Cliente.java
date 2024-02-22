package com.skylab.domain.rp.model.cliente;

import com.skylab.FormatValues;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.text.MessageFormat;

@Entity
@Table(name = "CLIENTES")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @Id
    @Column(name = "cli_001")
    private Integer id;
    @Column(name = "emp_001")
    private final int empresa = 1;
    @Column(name = "cli_002")
    private String nomeRazaoSocial;
    @Column(name = "cli_004")
    private String cpfCnpj;
    @Column(name = "cep_004")
    private String endereco;
    @Column(name = "cli_008")
    private String numero;
    @Column(name = "cli_009")
    private String complemento;
    @Column(name = "cep_003")
    private String bairro;
    @Column(name = "cidade_desc")
    private String cidade;
    @Column(name = "uf")
    private String uf;
    @Column(name = "cli_012")
    private String telefone;
    @Column(name = "celular1")
    private String celular1;
    @Column(name = "celular2")
    private String celular2;

    @Override
    public String toString() {
        String cabecalho = "";
        cabecalho = cabecalho.concat(verifyStringNullable(nomeRazaoSocial)) + "\n";
        cabecalho = cabecalho.concat(FormatValues.mascaraCpfCnpj(verifyStringNullable(cpfCnpj))) + "\n";
        cabecalho = cabecalho.concat(getEndereco()) + "\n";
        cabecalho = cabecalho.concat(FormatValues.mascaraTelefone(verifyStringNullable(telefone)));
        cabecalho = cabecalho.concat(FormatValues.mascaraTelefone(verifyStringNullable(celular1)));
        cabecalho = cabecalho.concat(FormatValues.mascaraTelefone(verifyStringNullable(celular2)));
        return cabecalho;
    }

    public String getEndereco() {
        return MessageFormat.format("{0} {1} {2} - {3}\n{4} / {5}",
                verifyStringNullable(endereco),
                verifyStringNullable(numero),
                verifyStringNullable(complemento),
                verifyStringNullable(bairro),
                verifyStringNullable(cidade),
                verifyStringNullable(uf));
    }

    private String verifyStringNullable(String value) {
        return value != null && !value.isEmpty() ? value : "";
    }

}
