package com.devsuperior.demo.repositories;

import com.devsuperior.demo.entities.City;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CityRepository extends JpaRepository<City, Long> {
    @Query("SELECT obj FROM City obj WHERE UPPER(obj.name) LIKE UPPER(CONCAT('%', :name, '%'))")
    Page<City> searchByName(String name, Pageable pageable);
}
