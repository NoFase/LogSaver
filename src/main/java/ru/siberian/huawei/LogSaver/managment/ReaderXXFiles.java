package ru.siberian.huawei.LogSaver.managment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.siberian.huawei.LogSaver.external.ListOfServers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ReaderXXFiles {
    private String fileName;
    private final Logger LOGGER = LoggerFactory.getLogger(ReaderXXFiles.class);

    public ReaderXXFiles(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<String> reading() {
        ArrayList<String> result = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(Paths.get("").toAbsolutePath().toString() + "/src/main/resources/include/" + fileName));
            while (br.ready()){
                result.add(br.readLine());
            }
            br.close();
            LOGGER.info("\tFinished reading data from the file," + fileName + " The numbers of lines read from the file: " + result.size());
        } catch (FileNotFoundException e) {
            LOGGER.warn("\t" + fileName + " - This file was not found. The file was searched for in the following path: "
                    + Paths.get("").toAbsolutePath().toString());
            e.printStackTrace();
        } catch (IOException e) {
            LOGGER.warn("\t We have some problem with reading the file: " + fileName);
            e.printStackTrace();
        }
        return result;
    }
}
