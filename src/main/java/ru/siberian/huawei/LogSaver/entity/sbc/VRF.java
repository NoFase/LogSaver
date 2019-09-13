package ru.siberian.huawei.LogSaver.entity.sbc;

import java.util.regex.Pattern;

public class VRF {
    private String nameOfVrf;
    private String type;
    private String target;
    private String city;

    public VRF(String nameOfVrf) {
        this.nameOfVrf = nameOfVrf;
        autoSet();
    }

    public String getNameOfVrf() {
        return nameOfVrf;
    }

    public String getType() {
        return type;
    }

    public String getTarget() {
        return target;
    }

    public String getCity() {
        return city;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setCity(String city) {
        this.city = city;
    }

    private void autoSet(){
        String pattern = "[0-9]*";
        if (nameOfVrf.contains("sig") || nameOfVrf.contains("SIG")) setType("sig");
        else if (nameOfVrf.contains("rtp") || nameOfVrf.contains("RTP")) setType("rtp");
        else setType("none");

        if (nameOfVrf.contains("access") || nameOfVrf.contains("client")) setTarget("out_access");
        else if (nameOfVrf.contains("men")) setTarget("out_internet");
        else if (nameOfVrf.contains("MSS")) setTarget("core_MSS");
        else if (nameOfVrf.contains("sx3k") || nameOfVrf.contains("softx")) setTarget("core");
        else setTarget("none");

        String[] partOfName = nameOfVrf.split("_");
        for (String part: partOfName){
            if (!part.contains("sig") && !part.contains("SIG") && !part.contains("rtp") && !part.contains("RTP")
                    && !part.contains("access") && !part.contains("client") && !part.contains("men")
                    && !part.contains("internet") && !part.contains("sx3k") && !part.contains("softx") &&
            !Pattern.matches(pattern, part)) setCity(part);
        }
        if (city == null) setCity("none");
    }
}
