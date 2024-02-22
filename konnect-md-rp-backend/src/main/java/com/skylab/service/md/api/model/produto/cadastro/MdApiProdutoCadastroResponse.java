package com.skylab.service.md.api.model.produto.cadastro;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class MdApiProdutoCadastroResponse {

    @JsonProperty("idCardapio")
    private List<Integer> codigosCardapio;

}
