package com.skylab.service.rp;

import com.skylab.domain.exception.EntidadeNaoEncontradaException;
import com.skylab.domain.rp.model.material.Material;
import com.skylab.domain.rp.repository.MaterialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterialService {

    @Autowired
    private MaterialRepository materiais;

    public List<Material> getProdutos(){
        return materiais.findAll();
    }

    public Material buscarProduto(Integer id) {
        return materiais.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException(""));
    }

    public Material salvar(Material material) {
        return materiais.save(material);
    }
}
