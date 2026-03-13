package br.sc.sctec.empreendimentos_api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Status {

    ATIVO,
    INATIVO;


    @JsonCreator
    public static Status from(String value) {
        if (value == null || value.isBlank()) return null;
        try {
            return Status.valueOf(value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Status inválido: " + value);
        }
    }

    @JsonValue
    public String toValue() { return this.name(); }
}

