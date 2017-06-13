package com.andrewYurkevich;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Андрей on 13.06.2017.
 */

@Controller
public class SimpleController {

    @RequestMapping("/")
    public ModelAndView show() {
        ModelAndView view = new ModelAndView();
        view.setViewName("index");
        return view;
    }

}
