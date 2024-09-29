package com.agsilva.os.dtos;

import com.agsilva.os.dominio.Tecnico;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

public record TecnicoDto(Integer id, String nome, @CPF String cpf, String telefone) implements Serializable {
    public TecnicoDto(Tecnico obj) {
        this(obj.getId(), obj.getNome(), obj.getCpf(), obj.getTelefone());
    }
}