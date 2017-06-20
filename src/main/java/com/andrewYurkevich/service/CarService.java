package com.andrewYurkevich.service;

import com.andrewYurkevich.model.Car;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * Created by Андрей on 20.06.2017.
 */
public interface CarService {

    Car addCar(Car car);

    List<Car> getAll();

    List<Car> getAll(Sort sort);

    Car getById(Integer id);

    Car getByName(String name);

    void delete(Integer id);

    Car editCar(Car car);

}
