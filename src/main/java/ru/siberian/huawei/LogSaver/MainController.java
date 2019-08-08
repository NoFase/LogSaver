package ru.siberian.huawei.LogSaver;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.siberian.huawei.LogSaver.external.ListOfServers;
import ru.siberian.huawei.LogSaver.managment.AnalyzerFromFiles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
//
//    @RequestMapping (value = "/all", method = RequestMethod.GET)
//    public String getAll(Map<String, Object> model){
//        ListOfServers listOfServers = new ListOfServers();
//        List<String> citys = new ArrayList<>();
//        for(HashMap.Entry<String, String> c: listOfServers.getServers().entrySet()){
//            citys.add(c.getValue());
//            System.out.println(c.getValue());
//        }
//        String[] s = (String[]) citys.toArray();
//        model.put("city", s);
//        return "search";
//    }

    @PostMapping("/search")
    public String printHello(Map<String, Object> model) {
        model.put("message", "Hello Spring MVC Framework!");
        return "hello";
    }


}
