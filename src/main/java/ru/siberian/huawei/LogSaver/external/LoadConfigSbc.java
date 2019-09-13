package ru.siberian.huawei.LogSaver.external;

import ru.siberian.huawei.LogSaver.entity.sbc.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoadConfigSbc {

    private Map<String, VRF> vrfs = new HashMap<>();
    private Map<String, Iaddr> iaddrs = new HashMap<>();
    private Map<String, Isiptg> isiptgs = new HashMap<>();
    private Map<String, Iofc> iofcs = new HashMap<>();
    private Map<String, Irt> irts = new HashMap<>();
    private Map<String, Caccallplc> caccallplcs = new HashMap<>();
    private Map<String, Cacplcset> cacplcsets = new HashMap<>();

    public LoadConfigSbc() {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(Paths.get("").toAbsolutePath().toString() + "/ALLME_20190910133235.txt"));
            while (br.ready()){
                String line = br.readLine();
                if (line.contains("ADD VRF:VRFNAME=")) searchingVrf(line);
                if (line.contains("ADD IADDR:ADDRNAME=")) searchingIaddr(line);
                if (line.contains("ADD ISIPTG:TGNAME=")) searchingIsiptg(line);
                if (line.contains("ADD IOFC:OFCNAME=")) searchingIofc(line);
                if (line.contains("ADD IRT:RTNAME=")) searchingIrt(line);
                if (line.contains("ADD CACCALLPLC:CALLPLCNAME=")) searchingCacllplcname(line);
                if (line.contains("ADD CACPLCSET:CACPLCSETNAME=")) searchingCacplcset(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, VRF> getVrfs() {
        return vrfs;
    }

    public Map<String, Iaddr> getIaddrs() {
        return iaddrs;
    }

    public Map<String, Isiptg> getIsiptgs() {
        return isiptgs;
    }

    public Map<String, Iofc> getIofcs() {
        return iofcs;
    }

    public Map<String, Irt> getIrts() {
        return irts;
    }

    public Map<String, Caccallplc> getCaccallplcs() {
        return caccallplcs;
    }

    public Map<String, Cacplcset> getCacplcsets() {
        return cacplcsets;
    }

    private void searchingVrf(String line){
        line = line.substring(16, line.length());
        String[] lineM = line.split(",");
        line = lineM[0].replace("\"", "");
        vrfs.put(line, new VRF(line));
    }

    private void searchingIaddr(String line){
        line = line.substring(19, line.length());
        String[] lineM = line.split(",");
        String nameIaddr = lineM[0].replace("\"", "");
        String ipAddress = "", vrfName ="";
        for (String part: lineM){
            if (part.contains("IPV4=")) ipAddress = splitAndReplace(part);
            if (part.contains("VRFNAME=")) vrfName = splitAndReplace(part);
        }
        iaddrs.put(nameIaddr, new Iaddr(nameIaddr, ipAddress, vrfName));
    }

    private void searchingIsiptg(String line){
        line = line.substring(11, line.length());
        String[] lineM = line.split(",");
        String nameIsiptg = splitAndReplace(lineM[0]);
        String lport = "";
        String pport = "";
        String pipV4 = "";
        String laddrn = "NONE";
        String meddn = "NONE";
        String chb = "NONE";
        String rnit = "NONE";
        List<String> cacPlcSetNameList = new ArrayList<>();
        String ipSetId = "NONE";
        String opSetId = "NONE";
        String qryitnport = "";

        for (String part: lineM){
            if (part.contains("LADDRN=")) laddrn = splitAndReplace(part);
            if (part.contains("LPORT=")) lport = splitAndReplace(part);
            if (part.contains("PIPV4=")) pipV4 = splitAndReplace(part);
            if (part.contains("PPORT=")) pport = splitAndReplace(part);
            if (part.contains("MEDDN=")) meddn = splitAndReplace(part);
            if (part.contains("CHB=")) chb = splitAndReplace(part);
            if (part.contains("RNIT=")) rnit = splitAndReplace(part);
            if (part.contains("CACPLCSETNAME1=")) cacPlcSetNameList.add(splitAndReplace(part));
            if (part.contains("CACPLCSETNAME2=")) cacPlcSetNameList.add(splitAndReplace(part));
            if (part.contains("CACPLCSETNAME3=")) cacPlcSetNameList.add(splitAndReplace(part));
            if (part.contains("IPSETID=")) ipSetId = splitAndReplace(part);
            if (part.contains("OPSETID=")) opSetId = splitAndReplace(part);
            if (part.contains("QRYITNPORT=")) qryitnport = splitAndReplace(part);
        }

        Isiptg isiptg = new Isiptg(nameIsiptg, lport, pipV4, pport);
            isiptg.setLaddrn(laddrn);
            isiptg.setMeddn(meddn);
            isiptg.setChb(chb);
            isiptg.setRnit(rnit);
            isiptg.setCacPlcSetNameList(cacPlcSetNameList);
            isiptg.setIpSetId(ipSetId);
            isiptg.setOpSetId(opSetId);
            isiptg.setQryitnport(qryitnport);
        isiptgs.put(nameIsiptg, isiptg);
    }

    private void searchingIofc(String line){
        line = line.substring(9, line.length());
        String[] lineM = line.split(",");
        String nameIofc = splitAndReplace(lineM[0]);
        String nameIsiptg = splitAndReplace(lineM[1]);
        iofcs.put(nameIofc, new Iofc(nameIofc, nameIsiptg));
    }

    private void searchingIrt(String line){
//        do not take into account the importance of priorities in IRT
        line = line.substring(7, line.length());
        String[] lineM = line.split(",");
        String nameIrt = splitAndReplace(lineM[0]);
        List<String> ofcNames = new ArrayList<>();
        String snot = "";
        for (int i = 1; i < lineM.length; i++) {
            if (lineM[i].contains("NAME=")) ofcNames.add(splitAndReplace(lineM[i]));
            if (lineM[i].contains("SNOT=")) snot = splitAndReplace(lineM[i]);
        }
        irts.put(nameIrt, new Irt(nameIrt, ofcNames, snot));
    }

    private void searchingCacllplcname(String line){
        line = line.substring(14, line.length());
        String[] lineM = line.split(",");
        String nameCacllplcname = splitAndReplace(lineM[0]);
        String msn = splitAndReplace(lineM[1]);
        String mcnt = splitAndReplace(lineM[4]);
        String mcr = splitAndReplace(lineM[11]);
        caccallplcs.put(nameCacllplcname, new Caccallplc(nameCacllplcname, msn, mcnt, mcr));
    }

    private void searchingCacplcset(String line){
        line = line.substring(13, line.length());
        String[] lineM = line.split(",");
        String nameCacplcsetname = splitAndReplace(lineM[0]);
        String cacplcsetmode = splitAndReplace(lineM[1]);
        String callplcname = splitAndReplace(lineM[2]);
        cacplcsets.put(nameCacplcsetname, new Cacplcset(nameCacplcsetname, cacplcsetmode, callplcname));
    }


    private String splitAndReplace(String line){
        return line.split("=")[1].replace("\"", "");
    }
}
