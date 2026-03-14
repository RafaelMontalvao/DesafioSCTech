package br.sc.sctec.empreendimentos_api.dto;


import lombok.Data;

@Data
public class MunicipioDTO {


    private String nome;
    private Long id;

    public MunicipioDTO(String nome, Long id) {
        this.nome = nome;
        this.id = id;
    }
}
