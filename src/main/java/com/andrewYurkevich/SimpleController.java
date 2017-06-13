package com.andrewYurkevich;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Андрей on 13.06.2017.
 */

@Controller
public class SimpleController {

    @RequestMapping("/")
    public @ResponseBody String index() {

        return "STRING!!!!";
    }

    @RequestMapping("/main")
    public ModelAndView main() {
        ModelAndView view = new ModelAndView();
        view.setViewName("mainPage");
        return view;
    }

}
