package com.agsilva.os.resource;

import com.agsilva.os.dtos.OsDto;
import com.agsilva.os.service.OsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping(value="/os")
public class OsResource {

    @Autowired
    private OsService osService;

    @GetMapping
    public ResponseEntity<List<OsDto>> findAll(){
        List<OsDto> list = osService.findAll().stream().map(obj -> new OsDto(obj)).collect(Collectors.toList());
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<OsDto> findById(@PathVariable Integer id){
        OsDto obj = new OsDto(osService.findById(id));
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping
    public ResponseEntity<OsDto> create(@Valid @RequestBody OsDto obj){
        obj = new OsDto(osService.create(obj));
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping
    public ResponseEntity<OsDto> update(@Valid @RequestBody OsDto obj){
        obj = new OsDto(osService.update(obj));
        return ResponseEntity.ok().body(obj);
    }
}
