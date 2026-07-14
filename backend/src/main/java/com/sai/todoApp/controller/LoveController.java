package com.sai.todoApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoveController {

    @GetMapping("/love")
    public String love() {
        return "I love you my baby uuuaaammm";
    }
}
