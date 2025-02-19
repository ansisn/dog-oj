package com.gcq.sandbox.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
@Slf4j
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

}
