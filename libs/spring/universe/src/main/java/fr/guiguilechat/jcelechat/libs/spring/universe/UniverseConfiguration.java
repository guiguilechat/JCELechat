package fr.guiguilechat.jcelechat.libs.spring.universe;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.universe")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.universe")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.universe")
public class UniverseConfiguration {

}