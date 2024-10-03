package com.agsilva.os.dtos;

import com.agsilva.os.dominio.Tecnico;
import jakarta.validation.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import java.io.Serializable;

public class TecnicoDto implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotEmpty(message = "O campo NOME é requerido!")
    private String nome;
    @CPF
    @NotEmpty(message = "O campo CPF é requerido!")
    private String cpf;
    @NotEmpty(message = "O campo TELEFONE é requerido!")
    private String telefone;

    public TecnicoDto(){

    }

    public TecnicoDto(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.telefone = obj.getTelefone();
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getTelefone() {
        return telefone;
    }
}