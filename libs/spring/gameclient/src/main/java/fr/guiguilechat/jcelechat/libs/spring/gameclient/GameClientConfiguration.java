package fr.guiguilechat.jcelechat.libs.spring.gameclient;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@AutoConfiguration
@ComponentScan("fr.guiguilechat.jcelechat.libs.spring.gameclient")
@EnableJpaRepositories("fr.guiguilechat.jcelechat.libs.spring.gameclient")
@EntityScan("fr.guiguilechat.jcelechat.libs.spring.gameclient")
@PropertySource("classpath:gameclient.properties")
public class GameClientConfiguration {
}
