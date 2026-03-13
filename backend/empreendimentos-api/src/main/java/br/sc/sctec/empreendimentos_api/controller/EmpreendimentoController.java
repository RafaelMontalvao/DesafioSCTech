package br.sc.sctec.empreendimentos_api.controller;


import br.sc.sctec.empreendimentos_api.dto.EmpreendimentoEdicaoRequest;
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

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<EmpreendimentoResponse>> listar(){
        List<Empreendimento> empreendimentos = service.consultar();
        Collections.sort(empreendimentos, Comparator.comparing(Empreendimento::getId));
        List<EmpreendimentoResponse> response = empreendimentos.stream().map(list -> mapper.map(list, EmpreendimentoResponse.class)).toList();
        return ResponseEntity.ok(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EmpreendimentoResponse> editar(@PathVariable Long id, @RequestBody @Valid EmpreendimentoEdicaoRequest empreendimentoRequest) {
        Empreendimento empreendimentoAtualizado = service.atualizar(id, empreendimentoRequest);
        EmpreendimentoResponse response = mapper.map(empreendimentoAtualizado, EmpreendimentoResponse.class);
        return ResponseEntity.ok(response);
    }




}
