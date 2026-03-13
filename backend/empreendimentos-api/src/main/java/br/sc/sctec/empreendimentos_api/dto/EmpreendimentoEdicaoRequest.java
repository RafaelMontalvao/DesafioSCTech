package br.sc.sctec.empreendimentos_api.dto;

import br.sc.sctec.empreendimentos_api.model.SegmentoAtuacao;
import br.sc.sctec.empreendimentos_api.model.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EmpreendimentoEdicaoRequest {


    private String nomeEmpreendimento;

    private String nomeResposnvael;

    private String municipio;

    private SegmentoAtuacao segmentoAtuacao;

    private String email;

    private Status status;
}
