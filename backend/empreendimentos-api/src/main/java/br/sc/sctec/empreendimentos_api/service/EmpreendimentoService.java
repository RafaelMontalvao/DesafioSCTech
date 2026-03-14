package br.sc.sctec.empreendimentos_api.service;

import br.sc.sctec.empreendimentos_api.Exception.RegistroExistenteException;
import br.sc.sctec.empreendimentos_api.Exception.RegistroNaoEncontradoException;
import br.sc.sctec.empreendimentos_api.dto.EmpreendimentoEdicaoRequest;
import br.sc.sctec.empreendimentos_api.dto.EmpreendimentoRequest;
import br.sc.sctec.empreendimentos_api.model.Empreendimento;
import br.sc.sctec.empreendimentos_api.repository.EmpreendimentoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmpreendimentoService {

   private final  EmpreendimentoRepository empreendimentoRepository;



   @Transactional
    public Empreendimento criar(Empreendimento empreendimento) {

        if(empreendimentoRepository.existsByNomeEmpreendimento(empreendimento.getNomeEmpreendimento()))
            throw new RegistroExistenteException();

        return empreendimentoRepository.save(empreendimento);


    }

    public List<Empreendimento> consultar() {
        return empreendimentoRepository.findAll();
    }

    public Empreendimento atualizar(Long id, @Valid EmpreendimentoEdicaoRequest empreendimentoRequest) {
       Optional<Empreendimento> empreendimento = empreendimentoRepository.findById(id);
       if(empreendimento.isEmpty())
           throw new RegistroNaoEncontradoException();
        Empreendimento empreendimentoAtualizado = empreendimento.get();

        if(empreendimentoRequest.getNomeEmpreendimento() != null && !empreendimentoRequest.getNomeEmpreendimento().isBlank()){
            empreendimentoAtualizado.setNomeEmpreendimento(empreendimentoRequest.getNomeEmpreendimento());
        }

        if(empreendimentoRequest.getEmail() != null && !empreendimentoRequest.getEmail().isBlank()){
            empreendimentoAtualizado.setEmail(empreendimentoRequest.getEmail());
        }
        if(empreendimentoRequest.getNomeResponsavel() != null && !empreendimentoRequest.getNomeResponsavel().isBlank()){
            empreendimentoAtualizado.setNomeResponsavel(empreendimentoRequest.getNomeResponsavel());
        }
        if(empreendimentoRequest.getMunicipio() != null && !empreendimentoRequest.getMunicipio().isBlank()){
            empreendimentoAtualizado.setMunicipio(empreendimentoRequest.getMunicipio());
        }
        if(empreendimentoRequest.getSegmentoAtuacao() != null){
            empreendimentoAtualizado.setSegmentoAtuacao(empreendimentoRequest.getSegmentoAtuacao());
        }

        if(empreendimentoRequest.getStatus() != null){
            empreendimentoAtualizado.setStatus(empreendimentoRequest.getStatus());
        }
        return empreendimentoRepository.save(empreendimentoAtualizado);

    }

    public void excluir(Long id) {
       boolean existe = empreendimentoRepository.existsById(id);
       if(!existe)
           throw new RegistroNaoEncontradoException();
        empreendimentoRepository.deleteById(id);

    }
}
