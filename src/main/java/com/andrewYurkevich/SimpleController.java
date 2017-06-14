package com.andrewYurkevich;

import com.andrewYurkevich.model.Car;
import com.andrewYurkevich.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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





    @RequestMapping({"/","/main"})
    public ModelAndView main() {
        ModelAndView view = new ModelAndView();
        view.setViewName("mainPage");
        return view;
    }

    @RequestMapping(value = "/newCar", method = RequestMethod.POST)
    public String createNewCar(Car car) {
        carRepository.save(car);
        return "mainPage";
    }

}
