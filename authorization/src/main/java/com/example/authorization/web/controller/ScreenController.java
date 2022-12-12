package com.example.authorization.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ScreenController {

    @GetMapping("/")
    public String firstScreen() {

        return "index";
    }

}
