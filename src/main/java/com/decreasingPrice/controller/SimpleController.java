package com.decreasingPrice.controller;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SimpleController {

    @RequestMapping("/")
//    @ResponseBody
    public String home(Model model) {
        String message = "new Model";
        return "hello";
    }

    @RequestMapping("/random")
    @ResponseBody
    public int numberOfUsers() {
        return new Random(10).nextInt();
    }
}
