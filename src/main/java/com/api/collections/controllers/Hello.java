package com.api.collections.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class Hello 
{
    @GetMapping("/hello")
    public String sayHi(@RequestParam(value = "name", defaultValue = "world") String name)
    {
        return String.format("Hello %s!", name);
    }
}
