package com.andrewYurkevich.service;

import com.andrewYurkevich.model.Car;
import com.andrewYurkevich.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Андрей on 20.06.2017.
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car addCar(Car car) {

        return carRepository.saveAndFlush(car);
    }

    @Override
    public List<Car> getAll() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getAll(Sort sort) {
        return carRepository.findAll(sort);
    }

    @Override
    public Car getById(Integer id) {
        return carRepository.findOne(id);
    }

    @Override
    public Car getByName(String name) {
        return carRepository.findByName(name);
    }

    @Override
    public void delete(Integer id) {
        carRepository.delete(id);
    }

    @Override
    public Car editCar(Car car) {

        Car currentCar = carRepository.findOne(car.getId());
        currentCar.setName(car.getName());
        currentCar.setSpeed(car.getSpeed());
        currentCar.setType(car.getType());
        currentCar.setVolume(car.getVolume());

        return carRepository.saveAndFlush(currentCar);
    }
}
