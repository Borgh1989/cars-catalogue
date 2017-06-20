package com.andrewYurkevich.repository;

import com.andrewYurkevich.model.Car;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Андрей on 13.06.2017.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{


    Car findByName(String name);




}
