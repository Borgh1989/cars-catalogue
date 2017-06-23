package com.andrewYurkevich.controller;

import com.andrewYurkevich.model.Car;
import com.andrewYurkevich.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

/**
 * Created by Андрей on 13.06.2017.
 */

@Controller
public class CarController {

    @Autowired
    private CarService carService;



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String main(Model model) {
        model.addAttribute("carList", this.carService.getAll());

        return "mainPage";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String findCarByName(@RequestParam("search") String carName, Model model) {

            Car findCar = this.carService.getByName(carName);
            if(findCar!=null) {
                model.addAttribute("viewCar", findCar);
                return "viewCar";
            }
            return "redirect:/";
    }

    @RequestMapping(value = "/sorting", method = RequestMethod.GET)                         //need test!!!!!!!!!!!
    public String sortingByParameters(@RequestParam("sorting") String sortParam, Model model) {
        Sort sort = new Sort(Sort.Direction.ASC, sortParam);
        List<Car> sortListCars = this.carService.getAll(sort);
        model.addAttribute("carList", sortListCars);


        return "mainPage";
    }

    @RequestMapping(value = "/viewCar", method = RequestMethod.GET)
    public String viewCar(int id, Model model) {

        model.addAttribute("viewCar", this.carService.getById(id));
        return "viewCar";
    }


    @RequestMapping(value = "/newCar", method = RequestMethod.GET)
    public String newCar(Model model) {

        model.addAttribute("car", new Car());

        return "newCar";
    }

    @RequestMapping(value = "/newCar", method = RequestMethod.POST)
    public String createCar(@Valid Car car, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {

            return "newCar";
        }
        this.carService.addCar(car);

        model.addAttribute("createdCar", car);
        return "createdCar";
    }



    @RequestMapping(value = "/updateCar", method = RequestMethod.GET)
    public String updateCar(int id, Model model) {

        model.addAttribute("editCar", this.carService.getById(id));
        return "updateCar";
    }

    @RequestMapping(value = "/updatedCar", method = RequestMethod.POST)
    public String updatedCar(int id, @Valid Car car, BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            model.addAttribute("editCar", this.carService.getById(id));
            return "updateCar";
        }

        Car currentCar = this.carService.editCar(car);

        model.addAttribute("createdCar", currentCar);
        return "updatedCar";
    }

    @RequestMapping(value = "/deleteCar", method = RequestMethod.GET)
    public String deleteCar(int id, Model model) {
        this.carService.delete(id);
        model.addAttribute("carList", this.carService.getAll());
        return "redirect:/";
    }



}
