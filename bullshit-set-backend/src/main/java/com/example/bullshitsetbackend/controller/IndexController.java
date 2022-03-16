package com.example.bullshitsetbackend.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController implements ErrorController{

    private static final String PATH = "/error";

    @RequestMapping("/error")
    public String handleError() {
        return "/";
    }

//    @Override
//    public String getErrorPath() {
//        return "/error";
//    }
}

