package ru.siberian.huawei.LogSaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import ru.siberian.huawei.LogSaver.entity.MessagesRepository;
import ru.siberian.huawei.LogSaver.external.ExternalData;
import ru.siberian.huawei.LogSaver.managment.LogTimer;


@SpringBootApplication
@EnableScheduling
public class LogSaverApplication {
	public static MessagesRepository repos;
	public static ConfigurableApplicationContext context;

	public static void main(String[] args) throws InterruptedException {
		context = SpringApplication.run(LogSaverApplication.class);
		MessagesRepository repository = context.getBean(MessagesRepository.class);
		repos = repository;


		ExternalData externalData = new ExternalData();
		LogTimer timer = new LogTimer(externalData, repository);
	}
}
