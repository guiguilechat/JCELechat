package fr.guiguilechat.jcelechat.libs.spring.evespringtypeattributes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class EveSpringTypeAttributesAppTest {

	public static void main(String[] args) {
		SpringApplication.run(EveSpringTypeAttributesAppTest.class, args);
	}

	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(EveSpringTypeAttributesAppTest.class);
	}

}
