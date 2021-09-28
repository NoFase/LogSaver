package ru.siberian.huawei.LogSaver.controllers.information;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.Map;

@Controller
public class InfController {
    @GetMapping("information/inf")
    public String main(Map<String, Object> model) throws IOException {
        model.put("some", "Информация по коммутаторам");
        return "information/inf";
    }
}
