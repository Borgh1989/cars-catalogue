package com.andrewYurkevich;

import com.andrewYurkevich.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;


import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Андрей on 21.06.2017.
 */
@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class SimpleControllerTest {
    @Mock
    CarService carService;

    @Mock
    private Model model;

    @InjectMocks
    private SimpleController sut;


    @Test
    public void main() throws Exception {
        sut.main(model);

    }

    @Test
    public void newCar() throws Exception {
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