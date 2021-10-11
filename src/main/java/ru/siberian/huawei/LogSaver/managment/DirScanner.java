package ru.siberian.huawei.LogSaver.managment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class DirScanner {
    private String serverName;
    private String folderName = "/XMLTemporary/";//возможно надо придумать плавающить путь до папки

    private final Logger LOGGER = LoggerFactory.getLogger(DirScanner.class);

    public DirScanner(){
    }

    public DirScanner(String serverName) {
        this.serverName = serverName;
    }

    public List<File> searchFileInFolder() {
        File dir = new File(folderName);
        List<File> list = new ArrayList<>();
        for (File file : dir.listFiles()) {
            if (file.isFile())
                list.add(file);
        }
        return list;
    }
    public String finding(){
        String fileName;
        switch (serverName){
            case "BLG":
                fileName = "FSS01BLG_FIX";
                break;
            case "CHB":
                fileName = "SoftX3000_Cheboksary";
                break;
            case "JAK":
                fileName = "FSS01YAK_FIX";
                break;
            case "KHB":
                fileName = "FSS01KHB_FIX";
                break;
            case "MUR":
                fileName = "SOFTFX_MUR";
                break;
            case "ORB":
                fileName = "SoftX3000_Orenburg";
                break;
            case "PKC":
                fileName = "FSS01PKC_FIX";
                break;
            case "PNZ":
                fileName = "SoftX3000_Penza";
                break;
            case "SAH":
                fileName = "FSS01SKH_FIX";
                break;
            case "SRT":
                fileName = "SoftX_Saratov";
                break;
            case "TOL":
                fileName = "SoftX_Tolyatti";
                break;
            case "ULK":
                fileName = "SoftX_Ulianovsk";
                break;
            case "VLD":
                fileName = "FSS01VLD_FIX";
                break;
            case "SPB":
                fileName = "SOFTFX_SPB";
                break;
            case "NNV":
                fileName = "SoftX3000_NNovgorod";
                break;
            case "8MR":
                fileName = "MOS_SX3000-1_8MARTA";
                break;
            case "SOK":
                fileName = "MOS_SX3000-2_SOKOLNIKI";
                break;
            case "BEL":
                fileName = "BEL_FIXSOFT3000";
                break;
            case "STV":
                fileName = "FSS_STV";
                break;
            case "KOS":
                fileName = "KOS_FIXSOFT3000";
                break;
            case "LIP":
                fileName = "LIP_FIXSOFT3000";
                break;
            case "VRN":
                fileName = "VRN_FIXSOFT3000";
                break;
            case "YAR":
                fileName = "YAR_FIXSOFT_3000";
                break;
            default:
                fileName = "This city is absent";
        }

        try {
            return searchFileInFolder()//поиск названия файла в зависимости от выбранного сервера
                    .stream()
                    .map(File::getName)
                    .collect(Collectors.toList())
                    .stream()
                    .filter(s -> s.contains(fileName))
                    .findFirst()
                    .get();
        } catch (NoSuchElementException e){
            LOGGER.info("Has not any files in folder " + folderName);
            return "Empty";
        }
    }
}
