package com.agsilva.os.resource;

import com.agsilva.os.dominio.Cliente;
import com.agsilva.os.dominio.Tecnico;
import com.agsilva.os.dtos.ClienteDto;
import com.agsilva.os.dtos.TecnicoDto;
import com.agsilva.os.service.ClienteService;
import com.agsilva.os.service.TecnicoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

    @Autowired
    private ClienteService clienteService;

    @GetMapping(value = "{id}")
    public ResponseEntity<ClienteDto> findByid(@PathVariable Integer id){
        ClienteDto objDto = new ClienteDto(clienteService.findById(id));
        return ResponseEntity.ok().body(objDto);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> findAll(){
      List<ClienteDto> listDto = clienteService.findAll()
              .stream().map(obj -> new ClienteDto(obj)).collect(Collectors.toList());
      return ResponseEntity.ok().body(listDto);
    /*  List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDto> listDto = new ArrayList<>();
        for(Tecnico obj: list){
            listDto.add(new TecnicoDto(obj));
        }
       list.forEach(obj -> listDto.add(new TecnicoDto(obj))); */
    }

    @PostMapping
    public ResponseEntity<ClienteDto> create(@Valid @RequestBody ClienteDto objDto){
        Cliente newObj = clienteService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<ClienteDto> update(@PathVariable Integer id, @Valid @RequestBody ClienteDto objDTO){
        ClienteDto newObj = new ClienteDto(clienteService.update(id, objDTO));
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
