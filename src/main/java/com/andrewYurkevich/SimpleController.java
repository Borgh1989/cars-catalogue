package com.andrewYurkevich;

import com.andrewYurkevich.model.Car;
import com.andrewYurkevich.repository.CarRepository;
import com.andrewYurkevich.service.CarService;
import com.andrewYurkevich.service.CarServiceImpl;
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
    private CarService carService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("carList", this.carService.getAll());

        return "mainPage";
    }

    @RequestMapping(value = "/newCar", method = RequestMethod.GET)
    public String newCar(Model model) {

        model.addAttribute("car", new Car());

        return "newCar";
    }

    @RequestMapping(value = "/viewCar", method = RequestMethod.POST)
    public String viewCar(@ModelAttribute Car car, Model model) {
            this.carService.addCar(car);

        model.addAttribute("createdCar", car);
        return "viewCar";
    }



    @RequestMapping(value = "/updateCar", method = RequestMethod.GET)
    public String updateCar(int id, Model model) {

        model.addAttribute("editCar", this.carService.getById(id));
        return "updateCar";
    }

    @RequestMapping(value = "/updatedCar", method = RequestMethod.POST)
    public String updatedCar(int id, Car car, Model model) {
          Car currentCar = this.carService.editCar(car);

        model.addAttribute("createdCar", currentCar);
        return "updatedCar";
    }

    @RequestMapping(value = "/deleteCar", method = RequestMethod.GET)
    public String deleteCar(int id, Model model) {
        this.carService.delete(id);
        model.addAttribute("carList", this.carService.getAll());
        return "mainPage";
    }



}
