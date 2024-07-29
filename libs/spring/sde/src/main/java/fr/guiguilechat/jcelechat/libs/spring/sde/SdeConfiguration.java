package fr.guiguilechat.jcelechat.libs.spring.sde;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.sde")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.sde")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.sde")
@PropertySource("classpath:sde.properties")
public class SdeConfiguration {
}
