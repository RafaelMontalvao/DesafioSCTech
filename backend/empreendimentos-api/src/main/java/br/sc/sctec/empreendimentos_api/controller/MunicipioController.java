package br.sc.sctec.empreendimentos_api.controller;

import br.sc.sctec.empreendimentos_api.dto.MunicipioDTO;
import br.sc.sctec.empreendimentos_api.service.MunicipioService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("municipios")
@CrossOrigin(origins = "http://localhost:5173")
public class MunicipioController {

    private final MunicipioService service;
    private final CacheManager cacheManager;

    public MunicipioController(MunicipioService service, CacheManager cacheManager) {
        this.service = service;
        this.cacheManager = cacheManager;
    }
    @GetMapping("/municipiossc")
    public ResponseEntity<List<MunicipioDTO>> getMunicipios() {

        Cache cache = cacheManager.getCache("municipiosSC");

        boolean cacheHit = cache.get(SimpleKey.EMPTY ) != null;

        List<MunicipioDTO> municipios = service.getMunicipiosSC();

        return ResponseEntity
                .ok()
                .header("X-Cache", cacheHit ? "HIT" : "MISS")
                .body(municipios);
    }




    }
