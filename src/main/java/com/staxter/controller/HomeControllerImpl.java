package com.staxter.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeControllerImpl implements HomeController {

    @Override
    public String helloWorld() {
        return "Hello World!";
    }
}
