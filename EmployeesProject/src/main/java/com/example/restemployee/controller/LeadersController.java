package com.example.restemployee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class LeadersController {


    @GetMapping("/leaders")
    public String showLeaders(){
        return "leaders";
    }
}
