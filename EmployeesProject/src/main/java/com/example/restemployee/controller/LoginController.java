package com.example.restemployee.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/myLoginForm")
    public String loginPage(){
        return "loginForm";
    }


    @GetMapping("/access-denied")
    public String accessDenied(){
        return "access-denied";
    }

    @GetMapping("/")
    public String landing(){
        return "landing";
    }
}
