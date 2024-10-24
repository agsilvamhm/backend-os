package com.agsilva.os.resource;

import com.agsilva.os.dtos.OsDto;
import com.agsilva.os.service.OsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/os")
public class OsResource {

    @Autowired
    private OsService osService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<OsDto> findById(@PathVariable Integer id){
        OsDto obj = new OsDto(osService.findById(id));
        return ResponseEntity.ok().body(obj);
    }
}
