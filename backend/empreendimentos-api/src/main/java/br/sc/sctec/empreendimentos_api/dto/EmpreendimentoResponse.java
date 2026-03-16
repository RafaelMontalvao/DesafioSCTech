package br.sc.sctec.empreendimentos_api.dto;

import br.sc.sctec.empreendimentos_api.model.SegmentoAtuacao;
import br.sc.sctec.empreendimentos_api.model.Status;
import lombok.Data;

@Data
public class EmpreendimentoResponse {

    private Long id;

    private String nomeEmpreendimento;

    private String nomeResponsavel;

    private String municipio;

    private SegmentoAtuacao segmentoAtuacao;

    private String email;

    private Status status;

}
