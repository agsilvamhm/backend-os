package com.agsilva.os.service;

import com.agsilva.os.dominio.Tecnico;
import com.agsilva.os.dtos.TecnicoDto;
import com.agsilva.os.exceptions.ObjectNotFoundException;
import com.agsilva.os.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Tecnico> findAll(){
        return tecnicoRepository.findAll();
    }

    public Tecnico create(TecnicoDto objDto){
        return tecnicoRepository.save(new Tecnico(objDto.id(), objDto.nome(), objDto.cpf(), objDto.telefone()));
    }
}
