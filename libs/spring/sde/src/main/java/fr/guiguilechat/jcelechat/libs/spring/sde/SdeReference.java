package fr.guiguilechat.jcelechat.libs.spring.sde;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.sde")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.sde")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.sde")
public class SdeReference {
}
