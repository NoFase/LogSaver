package ru.siberian.huawei.LogSaver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.siberian.huawei.LogSaver.entity.MessagesRepository;
import ru.siberian.huawei.LogSaver.external.ExternalData;
import ru.siberian.huawei.LogSaver.managment.LogTimer;
import ru.siberian.huawei.LogSaver.process.ConfiguratorCustomersOnSbc;
import ru.siberian.huawei.LogSaver.repository.CaccallplcRepository;


@SpringBootApplication
public class LogSaverApplication {
	public static MessagesRepository repos;
	public static ConfigurableApplicationContext context;

	public static void main(String[] args) {
		context = SpringApplication.run(LogSaverApplication.class);
		MessagesRepository repository = context.getBean(MessagesRepository.class);
		repos = repository;

//        new ConfiguratorCustomersOnSbc();

		ExternalData externalData = new ExternalData();
		LogTimer timer = new LogTimer(externalData, repository);
	}
}
