package com.agsilva.os.dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cliente extends Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;
    @ManyToMany(mappedBy = "cliente")
    private List<Os> list = new ArrayList<>();
    public Cliente() {
    }

    public Cliente(Integer id, String nome, String cpf, String telefone) {
        super(id, nome, cpf, telefone);
    }

    public List<Os> getList() {
        return list;
    }

    public void setList(List<Os> list) {
        this.list = list;
    }
}
