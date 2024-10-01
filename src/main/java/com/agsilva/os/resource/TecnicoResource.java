package com.agsilva.os.resource;

import com.agsilva.os.dominio.Tecnico;
import com.agsilva.os.dtos.TecnicoDto;
import com.agsilva.os.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoResource {

    @Autowired
    private TecnicoService tecnicoService;

    @GetMapping(value = "{id}")
    public ResponseEntity<TecnicoDto> findByid(@PathVariable Integer id){
        TecnicoDto objDto = new TecnicoDto(tecnicoService.findById(id));
        return ResponseEntity.ok().body(objDto);
    }

    @GetMapping
    public ResponseEntity<List<TecnicoDto>> findAll(){
      List<TecnicoDto> listDto = tecnicoService.findAll()
              .stream().map(obj -> new TecnicoDto(obj)).collect(Collectors.toList());
      return ResponseEntity.ok().body(listDto);
    /*  List<Tecnico> list = tecnicoService.findAll();
        List<TecnicoDto> listDto = new ArrayList<>();
        for(Tecnico obj: list){
            listDto.add(new TecnicoDto(obj));
        }
       list.forEach(obj -> listDto.add(new TecnicoDto(obj))); */
    }

    @PostMapping
    public ResponseEntity<TecnicoDto> create(@RequestBody TecnicoDto objDto){
        Tecnico newObj = tecnicoService.create(objDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
