package ru.siberian.huawei.LogSaver.managment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class LoadersDataForXML {
    private final Logger LOGGER = LoggerFactory.getLogger(LoadersDataForXML.class);

    static public List<FTPServersForUpload> ftpServersForUploads = new ArrayList<>();

    public LoadersDataForXML() {
        ArrayList<String> strings = new ReaderXXFiles().reading("XMLServerList.txt");
        LOGGER.info("load " + strings.size() + " strings");

        for (String string: strings){
            String[] params = string.split("\\s+");
            if (!params[0].contains("//"))
                ftpServersForUploads.add(new FTPServersForUpload(params[0], params[1], params[2], params[3], params[4]));
        }
        LOGGER.info("Loaded [" + ftpServersForUploads.size() + "] names commutators from: XMLServerList.txt" );
    }
}
