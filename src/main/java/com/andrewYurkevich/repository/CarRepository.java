package com.andrewYurkevich.repository;

import com.andrewYurkevich.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Андрей on 13.06.2017.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer>{
    
}
