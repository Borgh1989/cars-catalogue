package com.andrewYurkevich.service;

import com.andrewYurkevich.model.Car;
import com.andrewYurkevich.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


import static org.junit.Assert.*;

/**
 * Created by Андрей on 21.06.2017.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CarServiceImplTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarServiceImpl carService;


    @Test
    public void test_addCar_resultNewCar() throws Exception {
        //prepare
        Car expectedCar = mock(Car.class);
        when(carRepository.saveAndFlush(expectedCar)).thenReturn(expectedCar);
        //testing
        Car testCar = carService.addCar(expectedCar);
        //validate
        assertSame(expectedCar, testCar);
    }

    @Test
    public void test_getAllCars_resultListCars() throws Exception {
        //prepare
        List<Car> expetedCars = Arrays.asList(mock(Car.class));
        when(carRepository.findAll()).thenReturn(expetedCars);
        //testing
        List<Car> testListCars = carService.getAll();
        //validate
        assertSame(expetedCars, testListCars);
    }

    @Test
    public void test_getAl1CarsWithSort_resultSortedListCars() throws Exception {
        //prepare
        List<Car> expetedCars = Arrays.asList(mock(Car.class));
        Sort sort = mock(Sort.class);
        when(carRepository.findAll(sort)).thenReturn(expetedCars);
        //testing
        List<Car> testListCars = carService.getAll(sort);
        //validate
        assertSame(expetedCars, testListCars);

    }

    @Test
    public void test_getCarById_resultCarById() throws Exception {
        //prepare
        Car expectedCar = mock(Car.class);
        int id = 1;
        when(carRepository.findOne(id)).thenReturn(expectedCar);
        //testing
        Car testCar = carService.getById(id);
        //validate
        assertSame(expectedCar, testCar);
    }

    @Test
    public void test_getCarByName_resultCarByName() throws Exception {
        //prepare
        Car expectedCar = mock(Car.class);
        String name = "name";
        when(carRepository.findByName(name)).thenReturn(expectedCar);
        //testing
        Car testCar = carService.getByName(name);
        //validate
        assertSame(expectedCar, testCar);
    }

    @Test
    public void test_deleteCar_resultDeletedCar() throws Exception {
        //prepare
        int id = 1;
        //testing
        carService.delete(id);
        //validate
        Car car =  carService.getById(id);
        assertNull(car);
    }

    @Test
    public void test_editingCar_resultUpdatedCar() throws Exception {
        //prepare
        Car expectedCar = mock(Car.class);
        when(carRepository.findOne(expectedCar.getId())).thenReturn(expectedCar);
        when(carRepository.saveAndFlush(expectedCar)).thenReturn(expectedCar);
        //testing
        Car testCar = carService.editCar(expectedCar);
        testCar.setName(expectedCar.getName());
        testCar.setVolume(expectedCar.getVolume());
        testCar.setType(expectedCar.getType());
        testCar.setSpeed(expectedCar.getSpeed());
        //validate
        assertSame(expectedCar,testCar);
    }

}