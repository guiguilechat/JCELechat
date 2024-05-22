package fr.guiguilechat.jcelechat.programs.spring.mevetic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync(proxyTargetClass = true)
@EnableCaching(proxyTargetClass = true)
@EnableScheduling
@SpringBootApplication
@EnableJpaRepositories
@EntityScan()
public class MEvEticApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MEvEticApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MEvEticApp.class);
	}

}
