package com.ipbyhj.dev.text;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/hello")
    public String hello(@RequestParam String name, @RequestParam String id) {
        return "hello " + sampleService.getName() + name+" "+id;
    }
}


