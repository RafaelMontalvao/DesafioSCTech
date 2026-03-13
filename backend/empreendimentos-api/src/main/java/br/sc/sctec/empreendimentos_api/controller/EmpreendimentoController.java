package br.sc.sctec.empreendimentos_api.controller;


import br.sc.sctec.empreendimentos_api.dto.EmpreendimentoRequest;
import br.sc.sctec.empreendimentos_api.dto.EmpreendimentoResponse;
import br.sc.sctec.empreendimentos_api.model.Empreendimento;
import br.sc.sctec.empreendimentos_api.service.EmpreendimentoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/empreendimento")
@RequiredArgsConstructor
public class EmpreendimentoController {


    private final ModelMapper mapper;
    private final EmpreendimentoService service;




    @PostMapping
    public ResponseEntity<EmpreendimentoResponse> criar(@RequestBody  @Valid EmpreendimentoRequest empreendimentoRequest){
        Empreendimento empreendimento = mapper.map(empreendimentoRequest, Empreendimento.class);
        empreendimento = service.criar(empreendimento);
        EmpreendimentoResponse empreendimentoResponse = mapper.map(empreendimento, EmpreendimentoResponse.class);
        return  ResponseEntity.ok(empreendimentoResponse);
    };
}
