package com.andrewYurkevich;

import com.andrewYurkevich.model.Car;
import com.andrewYurkevich.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Андрей on 13.06.2017.
 */

@Controller
public class SimpleController {

    @Autowired
    CarRepository carRepository;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("carList", carRepository.findAll());

        return "mainPage";
    }

    @RequestMapping(value = "/newCar", method = RequestMethod.GET)
    public String newCar(Model model) {

        model.addAttribute("car", new Car());

        return "newCar";
    }

    @RequestMapping(value = "/viewCar", method = RequestMethod.POST)
    public String viewCar(@ModelAttribute Car car, Model model) {
        if(car.getId()==0)
            carRepository.saveAndFlush(car);
        else {
            Car currentCar = carRepository.findOne(car.getId());
            currentCar.setName(car.getName());
            currentCar.setSpeed(car.getSpeed());
            currentCar.setType(car.getType());
            currentCar.setVolume(car.getVolume());
            carRepository.saveAndFlush(currentCar);
        }

        model.addAttribute("createdCar", car);
        return "viewCar";
    }



    @RequestMapping(value = "/updateCar", method = RequestMethod.GET)
    public String updateCar(int id, Model model) {

        model.addAttribute("editCar", carRepository.findOne(id));
        return "updateCar";
    }

    @RequestMapping(value = "/updatedCar", method = RequestMethod.POST)
    public String updatedCar(int id, Car car, Model model) {
        Car currentCar = carRepository.findOne(id);
        currentCar.setName(car.getName());
        currentCar.setSpeed(car.getSpeed());
        currentCar.setType(car.getType());
        currentCar.setVolume(car.getVolume());

        carRepository.saveAndFlush(currentCar);
        model.addAttribute("createdCar", currentCar);
        return "updatedCar";
    }



}
