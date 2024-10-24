package com.agsilva.os.service;

import com.agsilva.os.dominio.Cliente;
import com.agsilva.os.dominio.Pessoa;
import com.agsilva.os.dominio.Tecnico;
import com.agsilva.os.dtos.ClienteDto;
import com.agsilva.os.dtos.TecnicoDto;
import com.agsilva.os.exceptions.DataIntegratyViolationException;
import com.agsilva.os.exceptions.ObjectNotFoundException;
import com.agsilva.os.repository.ClienteRepository;
import com.agsilva.os.repository.PessoaRepository;
import com.agsilva.os.repository.TecnicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Cliente findById(Integer id){
        Optional<Cliente> obj = clienteRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objecto não encontrado! Id: " + id + " ,Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }

    public Cliente create(ClienteDto objDto){
        if(findbyCPF(objDto) != null){
            throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        return clienteRepository.save(new Cliente(objDto.getId(), objDto.getNome(), objDto.getCpf(), objDto.getTelefone()));
    }

    public Cliente update(Integer id, @Valid ClienteDto objDTO) {
        Cliente oldObj = findById(id);
        if(findbyCPF(objDTO) != null && findbyCPF(objDTO).getId() != id){
           throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return clienteRepository.save(oldObj);
    }

    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getList().size() > 0 ){
            throw new DataIntegratyViolationException("Técnico possui ordens de serviço, não pode ser deletado!");
        }
        clienteRepository.deleteById(id);
    }

    private Pessoa findbyCPF(ClienteDto objDto){
        Pessoa obj = pessoaRepository.findByCPF(objDto.getCpf());
        if (obj != null){
            return obj;
        }
        return null;
    }

}
