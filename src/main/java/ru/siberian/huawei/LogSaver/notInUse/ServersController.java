package ru.siberian.huawei.LogSaver.notInUse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.siberian.huawei.LogSaver.external.AbrOfServers;

import java.util.Map;

@Controller
public class ServersController {
    @GetMapping("information/servers")
    public String main(Map<String, Object> model){
        model.put("some", "");
        model.put("abrServers", AbrOfServers.abrServers);
//        LOGGER.info("\t\tStart controller, by method main - " + AbrOfServers.abrServers);
        return "information/servers";
    }
}
