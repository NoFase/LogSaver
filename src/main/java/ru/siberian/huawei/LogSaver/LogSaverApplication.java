package ru.siberian.huawei.LogSaver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.siberian.huawei.LogSaver.entity.Messages;
import ru.siberian.huawei.LogSaver.entity.MessagesRepository;
import ru.siberian.huawei.LogSaver.external.ExternalData;
import ru.siberian.huawei.LogSaver.managment.LogTimer;

import java.util.Date;

@SpringBootApplication

public class LogSaverApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(LogSaverApplication.class);
		MessagesRepository repository = context.getBean(MessagesRepository.class);


		ExternalData externalData = new ExternalData();

		LogTimer timer = new LogTimer(externalData, repository);

//		repository.save(new Messages("SRT",  new Date(), "IM", "10.10.10.10", "LST TG:;"));
	}
}
