package com.agsilva.os.service;

import com.agsilva.os.dominio.Tecnico;
import com.agsilva.os.exceptions.ObjectNotFoundException;
import com.agsilva.os.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    public Tecnico findById(Integer id){
        Optional<Tecnico> obj = tecnicoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objecto não encontrado! Id: " + id + " ,Tipo: " + Tecnico.class.getName()));
    }
}
