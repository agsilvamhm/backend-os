package com.agsilva.os.service;

import com.agsilva.os.dominio.Tecnico;
import com.agsilva.os.dtos.TecnicoDto;
import com.agsilva.os.exceptions.DataIntegratyViolationException;
import com.agsilva.os.exceptions.ObjectNotFoundException;
import com.agsilva.os.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

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
        if(findbyCPF(objDto) != null){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        return tecnicoRepository.save(new Tecnico(objDto.getId(), objDto.getNome(), objDto.getCpf(), objDto.getTelefone()));
    }

    private Tecnico findbyCPF(TecnicoDto objDto){
        Tecnico obj = tecnicoRepository.findByCPF(objDto.getCpf());
        if (obj != null){
            return obj;
        }
        return null;
    }
}
