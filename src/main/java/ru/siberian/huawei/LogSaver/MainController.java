package ru.siberian.huawei.LogSaver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.siberian.huawei.LogSaver.managment.AnalyzerFromFiles;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.util.Map;
import javax.servlet.http.*;

@Controller
public class MainController {

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
        System.out.println(fileName);
        InputStreamReader reader = new InputStreamReader(filePart.getInputStream());
        int c;
        String line = "";
        int count = 0;
        while ((c=reader.read()) > 0) {
//            response.getWriter().print((char)c);
            line += (char)c;
//            System.out.println(count++);
//            System.out.println(line);
            new AnalyzerFromFiles(fileName, line);
        }
//        String[] lines = line.split("\n");
//        System.out.println(lines.length);
//        for (String l: lines) System.out.println(l);
    }

}
