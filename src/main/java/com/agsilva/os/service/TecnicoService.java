package com.agsilva.os.service;

import com.agsilva.os.dominio.Pessoa;
import com.agsilva.os.dominio.Tecnico;
import com.agsilva.os.dtos.TecnicoDto;
import com.agsilva.os.exceptions.DataIntegratyViolationException;
import com.agsilva.os.exceptions.ObjectNotFoundException;
import com.agsilva.os.repository.PessoaRepository;
import com.agsilva.os.repository.TecnicoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

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

    public Tecnico update(Integer id, @Valid TecnicoDto objDTO) {
        Tecnico oldObj = findById(id);
        if(findbyCPF(objDTO) != null && findbyCPF(objDTO).getId() != id){
           throw new DataIntegratyViolationException("CPF já cadastrado na base de dados!");
        }
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setTelefone(objDTO.getTelefone());
        return tecnicoRepository.save(oldObj);
    }

    public void delete(Integer id) {
        Tecnico obj = findById(id);
        if (obj.getList().size() > 0 ){
            throw new DataIntegratyViolationException("Técnico possui ordens de serviço, não pode ser deletado!");
        }
        tecnicoRepository.deleteById(id);
    }

    private Pessoa findbyCPF(TecnicoDto objDto){
        Pessoa obj = pessoaRepository.findByCPF(objDto.getCpf());
        if (obj != null){
            return obj;
        }
        return null;
    }

}
