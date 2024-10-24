package com.agsilva.os.service;

import com.agsilva.os.dominio.Os;
import com.agsilva.os.exceptions.ObjectNotFoundException;
import com.agsilva.os.repository.OsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OsService {

    @Autowired
    private OsRepository osRepository;

    public Os findById(Integer id){
        Optional<Os> obj = osRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Os.class.getName()));
    }
}
