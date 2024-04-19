package fr.guiguilechat.jcelechat.programs.spring.mevetic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
@ImportAutoConfiguration
// @Import({
// ConnectReference.class
// })
public class MEvEticApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MEvEticApp.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(MEvEticApp.class);
	}

	@PostConstruct
	public void init() {
		System.err.println(" mevetic loaded");
	}

}
