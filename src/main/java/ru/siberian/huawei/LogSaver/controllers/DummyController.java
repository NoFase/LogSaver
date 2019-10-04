package ru.siberian.huawei.LogSaver.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class DummyController {
    @GetMapping("/gag")
    public String main(Map<String, Object> model){
        model.put("some", "В разработке...");
        return "dummy";
    }
}
