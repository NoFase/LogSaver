package ru.siberian.huawei.LogSaver.managment;

import ru.siberian.huawei.LogSaver.service.TrunkGroup;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;

public class LoadingDataForTDM {

    private HashMap<String, TrunkGroup> trunks = new HashMap<>();

    public LoadingDataForTDM() {
        loadingFromFile();
    }

    private void loadingFromFile(){
        try {
//            System.out.println("start");
            BufferedReader br = new BufferedReader(new FileReader(Paths.get("").toAbsolutePath().toString() + "/TDMIU.txt"));
            while (br.ready()){
                String line = br.readLine();
                if (line.contains(" 22   ") || line.contains(" 23   ")) calculatingE1(line);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculatingE1(String string){
        String parameters[] = string.split("\\s+");


//        for (int i = 0; i < parameters.length; i++) {
//            System.out.print(i + " [" + parameters[i] + "] ");
//        }
//        System.out.println();
    }

    private void creatingEntityOfTDM(String string){

    }
}
