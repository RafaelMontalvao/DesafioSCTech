package br.sc.sctec.empreendimentos_api.dto;

import br.sc.sctec.empreendimentos_api.model.SegmentoAtuacao;
import br.sc.sctec.empreendimentos_api.model.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class EmpreendimentoRequest {


    @NotNull(message="Campo Obrigatório")
    private String nomeEmpreendimento;

    @NotNull(message="Campo Obrigatório")
    private String nomeResposnvael;

    @NotNull(message="Campo Obrigatório")
    private String municipio;

    @NotNull(message="Campo Obrigatório")
    private SegmentoAtuacao segmentoAtuacao;

    @NotNull(message="Campo Obrigatório")
    private String email;

    @NotNull(message = "Campo Obrigatório")
    private Status status;
}
