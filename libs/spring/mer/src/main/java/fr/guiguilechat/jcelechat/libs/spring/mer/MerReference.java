package fr.guiguilechat.jcelechat.libs.spring.mer;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.mer")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.mer")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.mer")
public class MerReference {
}
