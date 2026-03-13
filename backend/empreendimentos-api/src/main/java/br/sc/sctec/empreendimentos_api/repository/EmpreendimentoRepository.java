package br.sc.sctec.empreendimentos_api.repository;

import br.sc.sctec.empreendimentos_api.model.Empreendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface EmpreendimentoRepository extends JpaRepository<Empreendimento, Long> {

        boolean existsByNomeEmpreendimento(String nomeEmpreendimento);
}
