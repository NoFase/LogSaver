package ru.siberian.huawei.LogSaver.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.siberian.huawei.LogSaver.external.AbrOfServers;
import ru.siberian.huawei.LogSaver.managment.AnalyzerFromFiles;
import ru.siberian.huawei.LogSaver.managment.FilterBd;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class MainController {

    private AnalyzerFromFiles analyzerFromFiles;


//    ---------------------main menu----------------------
    @GetMapping("/")
    public String main(Map<String, Object> model){
        model.put("some", "Template main window");
        return "main";
    }

//    ---------------------loading menu----------------------
    @GetMapping("/loading")
    public String loading(Map<String, Object> model){
        model.put("some", "Страница для загрузки файлов с логами в TXT формате");
        return "loading";
    }

    @PostMapping("/loading")
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        request.setCharacterEncoding("UTF-8");
//        response.getWriter().println(request.getParameter("data"));
        Part filePart = request.getPart("file");
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        analyzerFromFiles = new AnalyzerFromFiles(fileName);
//        InputStreamReader reader = new InputStreamReader(filePart.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(filePart.getInputStream()));

        while (br.ready()) {
            analyzerFromFiles.checking(br.readLine());
        }
    }
//-------------------------SEARCH-------------------------------
    @GetMapping("/search")
    public String search(Map<String, Object> model){
        Object search = new Object();
        List<String> abrServers = AbrOfServers.abrServers;
        model.put("abrServers", abrServers);
        model.put("search", search);
        model.put("some", "Поиск логов на фиксированных коммутаторах");
        return "search";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String sign, @RequestParam String abrCity, @RequestParam String dateStart,
            @RequestParam String dateEnd, Map<String, Object> model){
        List<String> searcher = new FilterBd(abrCity, dateStart, dateEnd, sign).searching();
        List<String> abrServers = AbrOfServers.abrServers;
        model.put("abrServers", abrServers);
        model.put("message", searcher);
        model.put("some", "Поиск логов на фиксированных коммутаторах");
        return "search";
    }
}
