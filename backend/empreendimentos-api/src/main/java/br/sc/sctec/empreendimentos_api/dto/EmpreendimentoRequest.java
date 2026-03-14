package br.sc.sctec.empreendimentos_api.dto;

import br.sc.sctec.empreendimentos_api.model.SegmentoAtuacao;
import br.sc.sctec.empreendimentos_api.model.Status;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class EmpreendimentoRequest {


    @NotEmpty(message="Campo Obrigatório")
    private String nomeEmpreendimento;

    @NotEmpty(message="Campo Obrigatório")
    private String nomeResponsavel;

    @NotEmpty(message="Campo Obrigatório")
    private String municipio;

    @NotNull(message="Campo Obrigatório")
    private SegmentoAtuacao segmentoAtuacao;

    @NotEmpty(message="Campo Obrigatório")
    private String email;

    @NotNull(message = "Campo Obrigatório")
    private Status status;
}
