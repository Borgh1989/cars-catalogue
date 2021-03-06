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
import org.springframework.data.domain.Sort;
import org.springframework.ui.ExtendedModelMap;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

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
        BindingResult bindingResult = mock(BindingResult.class);
        when(carService.addCar(expectedCar)).thenReturn(expectedCar);
        //testing
        String pageName = sut.createCar(expectedCar, bindingResult, model);
        //validate
        assertEquals("createdCar", pageName);
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
        int id = 1;
        Car expectedCar = mock(Car.class);
        BindingResult bindingResult = mock(BindingResult.class);
        when(carService.editCar(expectedCar)).thenReturn(expectedCar);
        //testing
        String pageName = sut.updatedCar(id, expectedCar, bindingResult, model);
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
        assertEquals("redirect:/", pageName);
        assertSame(expectedCars, model.asMap().get("carList"));
    }

    @Test
    public void test_searchCarByName_resultCarByName() throws Exception {
        //prepare
        String name = "carByName";
        Car expectedCar = mock(Car.class);
        when(carService.getByName(name)).thenReturn(expectedCar);
        //testing
        String pageName = sut.findCarByName(name, model);
        //validate
        assertEquals("viewCar", pageName);
        assertSame(expectedCar, model.asMap().get("viewCar"));
    }

    @Test
    public void test_viewCarById_resultCarById() throws Exception {
        //prepare
        Car expectedCar = mock(Car.class);
        int id = 1;
        when(carService.getById(id)).thenReturn(expectedCar);
        //testing
        String pageName = sut.viewCar(id, model);
        //validate
        assertEquals("viewCar", pageName);
        assertSame(expectedCar, model.asMap().get("viewCar"));
    }

    @Test
    public void test_sortCarsByparam_resultSortedCarsList () throws Exception {
        //prepare
        Car testCar = mock(Car.class);
        String sortParam = "name";
        List<Car> expectedSortCars = Arrays.asList(testCar, testCar, testCar);


        Sort testSort = new Sort(Sort.Direction.ASC, sortParam);
        when(carService.getAll(testSort)).thenReturn(expectedSortCars);
        //testing
        String pageName = sut.sortingByParameters(sortParam, model);
        //validate
        assertEquals("mainPage", pageName);
        assertSame(expectedSortCars, model.asMap().get("carList"));

    }

}