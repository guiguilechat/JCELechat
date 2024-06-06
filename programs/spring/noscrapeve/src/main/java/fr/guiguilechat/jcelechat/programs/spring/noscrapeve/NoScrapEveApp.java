package fr.guiguilechat.jcelechat.programs.spring.noscrapeve;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableCaching
@EnableScheduling
@SpringBootApplication
public class NoScrapEveApp {

	public static void main(String[] args) {
		SpringApplication.run(NoScrapEveApp.class, args);
	}

}