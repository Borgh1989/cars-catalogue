package com.andrewYurkevich.controller;

import com.andrewYurkevich.controller.CarController;
import com.andrewYurkevich.model.Car;
import com.andrewYurkevich.repository.CarRepository;
import com.andrewYurkevich.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by Андрей on 21.06.2017.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class CarControllerTest {
    @Mock
    private CarService carService;


    private Model model;


    @Before
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
    }

    @InjectMocks
    private CarController sut;



    @Test
    public void main() throws Exception {
        //prepare
        List<Car> expectedCars = Arrays.asList(new Car(), new Car(), new Car());
        when(carService.getAll()).thenReturn(expectedCars);
        //testing
        String result = sut.main(model);
        //validate
        assertEquals("Message","mainPage", result);
        assertSame(expectedCars, model.asMap().get("carList"));

    }

    @Test
    public void test_addingNewCar_resultAddedCar() throws Exception {
        //prepare
        Car car = new Car();
        //testing
        String pageName = sut.newCar(model);
        //validate
        assertEquals("newCar", pageName);
        assertEquals(car, model.asMap().get("car"));

    }

    @Test
    public void viewCar() throws Exception {


    }

    @Test
    public void updateCar() throws Exception {
    }

    @Test
    public void updatedCar() throws Exception {
    }

    @Test
    public void deleteCar() throws Exception {
    }

}