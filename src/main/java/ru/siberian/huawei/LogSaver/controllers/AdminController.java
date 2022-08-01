package ru.siberian.huawei.LogSaver.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class AdminController {
    private final Logger LOGGER = LoggerFactory.getLogger(AdminController.class);

    @GetMapping("/admin")
    public String main(Map<String, Object> model) {

        model.put("some", "Поиск логов на SE2900");
        return "admin";
    }
}
