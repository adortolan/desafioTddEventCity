package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CityService {
    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public Page<CityDTO> searchByName(String name, Pageable pageable) {
        Page<City> cities = repository.searchByName(name, pageable);
        return  cities.map(CityDTO::new);
    }

    @Transactional
    public CityDTO insert(CityDTO dto) {
        City entity = new City();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CityDTO(entity);
    }

    @Transactional
    public void delete(Long id) {
        if(!repository.existsById(id)) {
            throw new IllegalArgumentException("Id not found");
        }

        repository.deleteById(id);
    }
}
