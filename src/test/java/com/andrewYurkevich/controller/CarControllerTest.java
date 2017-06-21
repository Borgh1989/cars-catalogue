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
        List<Car> expectedCars = Arrays.asList(mock(Car.class), mock(Car.class), mock(Car.class));
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
        Car expectedCar = new Car();
        //testing
        String pageName = sut.newCar(model);
        //validate
        assertEquals("newCar", pageName);
        assertEquals(expectedCar, model.asMap().get("car"));

    }

    @Test
    public void test_addCarToBase_reasultAddedCar() throws Exception {
        //prepare
        Car expectedCar = mock(Car.class);
        when(carService.addCar(expectedCar)).thenReturn(expectedCar);
        //testing
        String pageName = sut.viewCar(expectedCar, model);
        //validate
        assertEquals("viewCar", pageName);
        assertSame(expectedCar, model.asMap().get("createdCar"));

    }

    @Test
    public void test_findToUpdateCar_resultFindedCarToUpdate() throws Exception {
        //prepare
        Car expectedCar = mock(Car.class);
        int id = 1;
        when(carService.getById(id)).thenReturn(expectedCar);
        //testing
        String pageName = sut.updateCar(id, model);
        //validate
        assertEquals("updateCar", pageName);
        assertSame(expectedCar, model.asMap().get("editCar"));
    }

    @Test
    public void test_updatedCar_resultUpdatedCar() throws Exception {
        //prepare
        Car expectedCar = mock(Car.class);
        when(carService.editCar(expectedCar)).thenReturn(expectedCar);
        //testing
        String pageName = sut.updatedCar(expectedCar, model);
        //validate
        assertEquals("updatedCar", pageName);
        assertSame(expectedCar, model.asMap().get("createdCar"));
    }

    @Test
    public void test_deleteCar_resultDeletedCar() throws Exception {
        //prepare
        int id = 1;
        List<Car> expectedCars = Arrays.asList(new Car(), new Car(), new Car());
        when(carService.getAll()).thenReturn(expectedCars);
        //testing
        String pageName = sut.deleteCar(id, model);
        //validate
        assertEquals("mainPage", pageName);
        assertSame(expectedCars, model.asMap().get("carList"));
    }

}