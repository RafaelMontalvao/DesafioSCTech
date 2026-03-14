package br.sc.sctec.empreendimentos_api.service;

import br.sc.sctec.empreendimentos_api.dto.MunicipioDTO;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MunicipioService {

    private final RestTemplate restTemplate;

    public MunicipioService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Cacheable("municipiosSC")
    public List<MunicipioDTO> getMunicipiosSC() {
        String url = "https://servicodados.ibge.gov.br/api/v1/localidades/estados/SC/municipios"; // coloque a API que retorna os municípios
        MunicipioDTO[] response = restTemplate.getForObject(url, MunicipioDTO[].class);

        // extrai só o nome e remove duplicatas
        return Arrays.stream(response)
                .map(m -> new MunicipioDTO(m.getNome(),m.getId()))
                .distinct()
                .sorted((a,b) -> a.getNome().compareToIgnoreCase(b.getNome()))
                .collect(Collectors.toList());
    }
}
