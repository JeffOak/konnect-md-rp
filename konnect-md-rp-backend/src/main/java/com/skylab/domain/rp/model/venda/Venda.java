package com.skylab.domain.rp.model.venda;

import com.skylab.domain.rp.model.cliente.Cliente;
import com.skylab.domain.rp.model.contareceber.ContaReceber;
import com.skylab.domain.rp.model.venda.converter.OrigemVendaConverter;
import com.skylab.domain.rp.model.venda.converter.StatusVendaConverter;
import com.skylab.domain.rp.model.venda.converter.TipoFiscalConverter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "VENDA")
@Getter
@Builder
@AllArgsConstructor
public class Venda {

    @Id
    @Column(name = "ven_001")
    private Integer vendaId;
    @Column(name = "emp_001")
    private final int empresaId = 1;
    @Column(name = "ven_004")
    private Timestamp data;
    @Column(name = "sit_001")
    @Convert(converter = StatusVendaConverter.class)
    private StatusVenda status;
    @Column(name = "ven_007")
    private BigDecimal desconto;
    @Column(name = "ven_008")
    private BigDecimal acrescimo;
    @Column(name = "ven_009")
    private BigDecimal total;
    @Column(name = "terminal_abertura")
    private String terminalAbertura;
    @Column(name = "nome_cliente")
    private String nomeCliente;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "ven_001")
    private final List<ItemVenda> itens = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "cli_001")
    private Cliente cliente;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_venda")
    private List<VendaPagamentoAntecipado> vendaPagamentoAntecipado;
    @OneToMany
    @JoinColumn(name = "id_venda")
    private List<ContaReceber> contasReceber;
    @Transient
    private BigDecimal troco;
    @Convert(converter = TipoFiscalConverter.class)
    private TipoFiscal tipoFiscal;
    @Column(name = "xml_cfe")
    private String xmlCfe;
    @Column(name = "ven_024")
    @Convert(converter = OrigemVendaConverter.class)
    private OrigemVenda origemVenda;
    @Column(name = "b_taxa_entrega")
    private boolean taxaEntrega;
    @Column(name = "ven_027")
    private String observacao;
    @Column(name = "painel_senha")
    private Long painelSenha;

    // CAMPOS DESCONHECIDOS
    @Column(name = "ven_029")
    private int idSecundario;
    @Column(name = "ven_002")
    private final int ven002 = 0;
    @Column(name = "ven_023")
    private final String ven023 = "N";

    public Venda() {
        troco = BigDecimal.ZERO;
    }


    public BigDecimal getAcrescimo() {
        return acrescimo != null ? acrescimo : BigDecimal.ZERO;
    }

    public BigDecimal getDesconto() {
        return desconto != null ? desconto : BigDecimal.ZERO;
    }

    public BigDecimal getSubtotal() {
        return total.subtract(getAcrescimo()).add(getDesconto());
    }

    public List<ItemVenda> getItens() {
        return itens.stream().filter(i -> i.getSituacao() == 4).collect(Collectors.toList());
    }


    private void verificarTipoFiscal() {

    }

    public void setItens(List<ItemVenda> itens) {
        this.itens.clear();
        if (itens != null) {
            this.itens.addAll(itens);
        }
    }


}


