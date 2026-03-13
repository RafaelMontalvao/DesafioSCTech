package br.sc.sctec.empreendimentos_api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum SegmentoAtuacao {

    TECNOLOGIA,
    COMERCIO,
    INDUSTRIA,
    SERVICO,
    AGRONEGOCIO;

    @JsonCreator
    public static SegmentoAtuacao from(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return SegmentoAtuacao.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("SegmentoAtuacao inválido: " + value);
        }
    }

    @JsonValue
    public String toValue() { return this.name(); }
}