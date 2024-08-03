package fr.guiguilechat.jcelechat.libs.spring.conflicts;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.conflicts")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.conflicts")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.conflicts")
@PropertySource("classpath:esiconflicts.properties")
public class ConflictsConfiguration {

}
