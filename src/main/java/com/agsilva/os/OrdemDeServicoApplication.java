package com.agsilva.os;

import com.agsilva.os.dominio.*;
import com.agsilva.os.repository.ClienteRepository;
import com.agsilva.os.repository.OsRepository;
import com.agsilva.os.repository.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class OrdemDeServicoApplication implements CommandLineRunner {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private OsRepository osRepository;

	public static void main(String[] args) {
		SpringApplication.run(OrdemDeServicoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tecnico t1 = new Tecnico(null, "Valdir Cesar", "144.785.300-84", "(88) 98888-8888");
		Cliente c1 = new Cliente(null, "Betina Campos", "598.508.200-80", "(88) 99999-7777");
		Os os1 = new Os(null, Prioridade.ALTA, "Teste create OS", Status.ANDAMENTO, t1, c1);

		t1.getList().add(os1);
		c1.getList().add(os1);

		tecnicoRepository.saveAll(Arrays.asList(t1));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}
}
