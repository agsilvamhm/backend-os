package com.agsilva.os.service;

import com.agsilva.os.dominio.*;
import com.agsilva.os.dtos.OsDto;
import com.agsilva.os.exceptions.ObjectNotFoundException;
import com.agsilva.os.repository.OsRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OsRepository osRepository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Os findById(Integer id){
        Optional<Os> obj = osRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Os.class.getName()));
    }

    public List<Os> findAll(){
        return osRepository.findAll();
    }

    public Os create(@Valid OsDto obj) {
        return fromDto(obj);
    }

    public Os update(OsDto obj) {
        findById(obj.getId());
        return fromDto(obj);
    }

    private Os fromDto(OsDto obj){
        Os newObj = new Os();
        newObj.setId(obj.getId());
        newObj.setObservacoes(obj.getObservacoes());
        newObj.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
        newObj.setStatus(Status.toEnum(obj.getStatus()));
        Tecnico tec = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        newObj.setTecnico(tec);
        newObj.setCliente(cliente);

        if (newObj.getStatus().getCod().equals(2)){
            newObj.setDataFechamento(LocalDateTime.now());
        }
        return osRepository.save(newObj);
    }
}