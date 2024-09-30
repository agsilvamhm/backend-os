package com.agsilva.os.resource;

import com.agsilva.os.dominio.Tecnico;
import com.agsilva.os.dtos.TecnicoDto;
import com.agsilva.os.service.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
