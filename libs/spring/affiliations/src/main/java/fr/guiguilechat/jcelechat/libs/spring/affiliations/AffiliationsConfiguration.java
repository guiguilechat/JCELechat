package fr.guiguilechat.jcelechat.libs.spring.affiliations;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.affiliations")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.affiliations")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.affiliations")
@PropertySource("classpath:esiaffiliations.properties")
public class AffiliationsConfiguration {

}