package com.skylab.domain.rp.model.material;

import com.skylab.domain.rp.model.material.converter.ImpressoraProducaoConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "materiais")
@Getter
@Setter
public class Material {

    @Id
    @Column(name = "mat_001")
    private Integer id;
    @Column(name = "mat_004")
    private String codigoAuxiliar;
    @Column(name = "mat_003")
    private String descricao;
    @Column(name = "dat_001_2")
    private Timestamp ultimaAlteracao;
    @Column(name = "mat_008")
    private BigDecimal valor;
    @Column(name = "sit_001")
    private int situacao;
    @Transient
    private boolean status;
    @Column(name = "mat_021")
    @Convert(converter = ImpressoraProducaoConverter.class)
    private ImpressoraProducao impressora1;
    @Column(name = "mat_022")
    @Convert(converter = ImpressoraProducaoConverter.class)
    private ImpressoraProducao impressora2;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "materiais_opcional",
            joinColumns = @JoinColumn(name = "id_material"),
            inverseJoinColumns = @JoinColumn(name = "id_opcional"))
    private List<Opcional> opcionais;

    public String getCodigo() {
        return codigoAuxiliar != null && !codigoAuxiliar.isEmpty()
                ? codigoAuxiliar : Integer.toString(id);
    }

    @PostLoad
    private void setStatus() {
        status = situacao == 4;
    }

}
