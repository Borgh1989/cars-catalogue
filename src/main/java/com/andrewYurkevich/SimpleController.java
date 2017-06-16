package com.andrewYurkevich;

import com.andrewYurkevich.model.Car;
import com.andrewYurkevich.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
        model.addAttribute("createdCar", car);
        carRepository.saveAndFlush(car);
        return "viewCar";
    }



    @RequestMapping(value = "/updateCAr", method = RequestMethod.POST)
    public String updateCar(@ModelAttribute Car car) {

        carRepository.saveAndFlush(car);
        return "updateCar";
    }



}
