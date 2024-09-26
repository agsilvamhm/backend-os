package com.agsilva.os.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Tecnico extends Pessoa{
    @OneToMany(mappedBy = "tecnico")
    private List<Os> list = new ArrayList<>();
    public Tecnico() {
    }

    public Tecnico(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }

    public List<Os> getList() {
        return list;
    }

    public void setList(List<Os> list) {
        this.list = list;
    }
}
