package fr.guiguilechat.jcelechat.libs.spring.evehistory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import fr.guiguilechat.jcelechat.libs.spring.evehistory.services.MarketFetchService;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@EnableScheduling
@Slf4j
public class EveHistoryApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EveHistoryApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EveHistoryApp.class);
	}

	boolean addData = true;

	@Bean
	public CommandLineRunner initDB(MarketFetchService mft) {
		return args -> {
			if (addData) {
				// add the forge
				boolean added = mft.addRegion(10000002);
				log.info("startup added TheForge : " + added);
			}
		};
	}
}
