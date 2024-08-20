package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.CityDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CityService {
    @Autowired
    private CityRepository repository;

    @Transactional(readOnly = true)
    public List<CityDTO> findAll() {
        List<City> cities = repository.findAll(Sort.by("name"));
        return cities.stream().map(x -> new CityDTO(x)).collect(Collectors.toList());
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
