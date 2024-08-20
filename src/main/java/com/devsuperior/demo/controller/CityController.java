package com.devsuperior.demo.controller;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.services.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/cities")
public class CityController {
    @Autowired
    private CityService service;

    @GetMapping
    public ResponseEntity<Page<CityDTO>> findAll( @RequestParam(value = "name", defaultValue = "") String name,
                                                  Pageable pageable) {
        Page<CityDTO> list = service.searchByName(name, pageable);
        return ResponseEntity.ok(list);
    }

    @PostMapping
    public ResponseEntity<CityDTO> insert(@RequestBody CityDTO dto) {
        CityDTO newDto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newDto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(newDto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
