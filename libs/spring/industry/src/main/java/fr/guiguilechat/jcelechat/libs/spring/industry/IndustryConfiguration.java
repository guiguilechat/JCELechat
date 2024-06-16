package fr.guiguilechat.jcelechat.libs.spring.industry;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.industry")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.industry")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.industry")
// @PropertySource("classpath:project.properties")
public class IndustryConfiguration {

}
