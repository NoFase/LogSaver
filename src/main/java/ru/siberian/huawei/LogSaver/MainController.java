package ru.siberian.huawei.LogSaver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.siberian.huawei.LogSaver.external.ListOfServers;
import ru.siberian.huawei.LogSaver.managment.AnalyzerFromFiles;
import ru.siberian.huawei.LogSaver.managment.FilterBd;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.*;
import javax.servlet.http.*;

@Controller
public class MainController {

    private AnalyzerFromFiles analyzerFromFiles;

//    ---------------------main menu----------------------
    @GetMapping
    public String main(Map<String, Object> model){
        model.put("some", "Template main window");
        return "main";
    }

    @PostMapping
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
        model.put("search", search);
        return "search";
    }

    @PostMapping("filter")
    public String filter(@RequestParam String sign, @RequestParam String abrCity, @RequestParam String dateStart,
            @RequestParam String dateEnd, Map<String, Object> model){
//        String out = abrCity + " " + sign + " " + dateStart + " " + dateEnd;
        List<String> searcher = new FilterBd(abrCity, dateStart, dateEnd, sign).searching();
//        for (String out: searcher) {
            model.put("message", searcher);
//        }
//        model.put("asign", "TRATATA");
        return "search";
    }
}
