package ru.siberian.huawei.LogSaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.siberian.huawei.LogSaver.entity.MessagesRepository;
import ru.siberian.huawei.LogSaver.external.ExternalData;
import ru.siberian.huawei.LogSaver.managment.services.CleanerFolder;
import ru.siberian.huawei.LogSaver.managment.services.FTPManager;
import ru.siberian.huawei.LogSaver.managment.LogTimer;


@SpringBootApplication
public class LogSaverApplication {
	public static MessagesRepository repos;
	public static ConfigurableApplicationContext context;
//	public static final Logger LOGGER = LoggerFactory.getLogger(LogSaverApplication.class);

	public static void main(String[] args) throws InterruptedException {
		context = SpringApplication.run(LogSaverApplication.class);
		MessagesRepository repository = context.getBean(MessagesRepository.class);
		repos = repository;

//        new ConfiguratorCustomersOnSbc();

		ExternalData externalData = new ExternalData();
		LogTimer timer = new LogTimer(externalData, repository);

//		FTPManager ftpManager = new FTPManager();
//		ftpManager.connectToFTP();
//
//		ftpManager.disconnect();
		new CleanerFolder();
	}
}
