package br.sc.sctec.empreendimentos_api.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name= " EMPREENDIMENTOS")
public class Empreendimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeEmpreendimento;

    private String nomeResponsavel;

    private String municipio;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SegmentoAtuacao segmentoAtuacao;

    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;




}
