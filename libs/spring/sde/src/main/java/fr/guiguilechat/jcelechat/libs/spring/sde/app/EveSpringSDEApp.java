package fr.guiguilechat.jcelechat.libs.spring.sde.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication(scanBasePackages = "fr.guiguilechat.jcelechat.libs.spring.sde")
public class EveSpringSDEApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EveSpringSDEApp.class, args);
	}

// @Override
// protected SpringApplicationBuilder configure(SpringApplicationBuilder
// builder) {
// return builder.sources(EveSpringSDEApp.class);
// }

}
