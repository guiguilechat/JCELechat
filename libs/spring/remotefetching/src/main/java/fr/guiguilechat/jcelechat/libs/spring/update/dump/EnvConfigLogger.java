package fr.guiguilechat.jcelechat.libs.spring.update.dump;

import java.util.Collection;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class EnvConfigLogger {

	@Lazy
	private final ConfigurableEnvironment configurableEnvironment;

	@PostConstruct
	protected void dumpEnvironment() {
		log.trace("application properties");
		configurableEnvironment.getPropertySources()
		    .stream()
		    .filter(MapPropertySource.class::isInstance)
		    .map(ps -> ((MapPropertySource) ps).getSource().keySet())
		    .flatMap(Collection::stream)
		    .distinct()
		    .sorted()
		    .forEach(key -> log.trace(" {}={}", key, configurableEnvironment.getProperty(key)));
	}

}
