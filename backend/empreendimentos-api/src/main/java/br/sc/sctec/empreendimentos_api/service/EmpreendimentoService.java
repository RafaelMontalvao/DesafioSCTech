package br.sc.sctec.empreendimentos_api.service;

import br.sc.sctec.empreendimentos_api.Exception.RegistroExistenteException;
import br.sc.sctec.empreendimentos_api.model.Empreendimento;
import br.sc.sctec.empreendimentos_api.repository.EmpreendimentoRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
