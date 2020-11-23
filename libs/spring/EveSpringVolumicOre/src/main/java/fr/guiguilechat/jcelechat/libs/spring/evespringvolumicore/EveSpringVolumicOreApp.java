package fr.guiguilechat.jcelechat.libs.spring.evespringvolumicore;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class EveSpringVolumicOreApp extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EveSpringVolumicOreApp.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
	return builder.sources(EveSpringVolumicOreApp.class);	
	}

}
