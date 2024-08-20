package com.devsuperior.demo.services;

import com.devsuperior.demo.dto.EventDTO;
import com.devsuperior.demo.entities.City;
import com.devsuperior.demo.entities.Event;
import com.devsuperior.demo.repositories.CityRepository;
import com.devsuperior.demo.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = false)
    public EventDTO update(Long id, EventDTO dto) {
        if(!repository.existsById(id)) {
            throw new IllegalArgumentException("Id not found");
        }

        Event entity = repository.getReferenceById(id);
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());

        City city = cityRepository.getReferenceById(dto.getCityId());
        entity.setCity(city);
        entity = repository.save(entity);
        return new EventDTO(entity);
    }
}
