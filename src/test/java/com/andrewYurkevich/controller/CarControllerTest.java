package com.andrewYurkevich.controller;

import com.andrewYurkevich.model.Car;
import com.andrewYurkevich.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import java.util.Arrays;
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

    @InjectMocks
    private CarController sut;


    private Model model;

    @Before
    public void setUp() throws Exception {
        model = new ExtendedModelMap();
    }



    @Test
    public void test_getAllCarsInMain_resultListCars() throws Exception {
        //prepare
        List<Car> expectedCars = Arrays.asList(new Car(), new Car(), new Car());
        when(carService.getAll()).thenReturn(expectedCars);
        //testing
        String pageName = sut.main(model);
        //validate
        assertEquals("Message","mainPage", pageName);
        assertSame(expectedCars, model.asMap().get("carList"));

    }

    @Test
    public void test_createNewCar_resultNewCar() throws Exception {
        //prepare
        Car car = new Car();
        //testing
        String pageName = sut.newCar(model);
        //validate
        assertEquals("newCar", pageName);
        assertEquals(car, model.asMap().get("car"));

    }

    @Test
    public void test_addCarToBase_reasultAddedCar() throws Exception {
        //prepare
        Car car = new Car();
        when(carService.addCar(car)).thenReturn(car);
        //testing
        String pageName = sut.viewCar(car, model);
        //validate
        assertEquals("viewCar", pageName);
        assertSame(car, model.asMap().get("createdCar"));

    }

    @Test
    public void test_findToUpdateCar_resultFindedCarToUpdate() throws Exception {
        //prepare
        Car car = new Car();
        int id = 1;
        when(carService.getById(id)).thenReturn(car);
        //testing
        String pageName = sut.updateCar(id, model);
        //validate
        assertEquals("updateCar", pageName);
        assertSame(car, model.asMap().get("editCar"));
    }

    @Test
    public void test_updatedCar_resultUpdatedCar() throws Exception {
        //prepare
        Car car = new Car();
        int id = 1;
        when(carService.editCar(car)).thenReturn(car);
        //testing
        String pageName = sut.updatedCar(id, car, model);
        //validate
        assertEquals("updatedCar", pageName);
        assertSame(car, model.asMap().get("createdCar"));
    }

    @Test
    public void test_deleteCar_resultDeletedCar() throws Exception {
        //prepare
        int id = 1;
        Car car = new Car();

        List<Car> expectedCars = Arrays.asList(new Car(), new Car(), new Car());
        when(carService.getAll()).thenReturn(expectedCars);
        //testing
        String pageName = sut.deleteCar(id, model);
        //validate
        assertEquals("mainPage", pageName);
        assertSame(expectedCars, model.asMap().get("carList"));
    }

}