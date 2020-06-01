package com.staxter.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface HomeController {

    @GetMapping(value = "/")
    String index();
}
