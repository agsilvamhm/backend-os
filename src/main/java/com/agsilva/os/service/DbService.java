package com.agsilva.os.service;

import com.agsilva.os.dominio.*;
import com.agsilva.os.repository.ClienteRepository;
import com.agsilva.os.repository.OsRepository;
import com.agsilva.os.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DbService {
    @Autowired
    private TecnicoRepository tecnicoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private OsRepository osRepository;

    public void instanciaDb(){
        Tecnico t1 = new Tecnico(null, "Valdir Cesar", "144.785.300-84", "(88) 98888-8888");
        Tecnico t2 = new Tecnico(null, "Linus Toward", "641.760.040-88", "(88) 94545-4545");

        Cliente c1 = new Cliente(null, "Betina Campos", "598.508.200-80", "(88) 99999-7777");
        Os os1 = new Os(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

        t1.getList().add(os1);
        c1.getList().add(os1);

        tecnicoRepository.saveAll(Arrays.asList(t1,t2));
        clienteRepository.saveAll(Arrays.asList(c1));
        osRepository.saveAll(Arrays.asList(os1));
    }
}
