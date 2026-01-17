package fr.guiguilechat.jcelechat.libs.spring.update.dump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.manager.EntityUpdater;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.UpdateScheduler;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ManagerConfigLogger {

	private final UpdateScheduler s;

	@PostConstruct
	protected void debugConfig() {
		log.trace("configuration of {} registered updaters, with manager skip={} defaultSkip={} updatedDelay={}",
		    s.getFetchedServices().orElse(List.of()).size(), s.isSkip(), s.isDefaultSkip(), s.getUpdatedDelay());
		Map<String, List<String>> propertiesPrefixToServices = new HashMap<>();
		log.trace("{} active services :",
		    s.getFetchedServices().orElse(List.of()).stream().filter(l -> !s.shouldSkip(l)).count());
		List<EntityUpdater> inactive = new ArrayList<>();
		for (EntityUpdater l : s.getFetchedServices().orElse(List.of())) {
			if (s.shouldSkip(l)) {
				inactive.add(l);
			} else {
				log.trace(" {} : {}={}", l.serviceName(), l.propertiesPrefix(), l.propertiesAsString());
			}
			propertiesPrefixToServices.computeIfAbsent(l.propertiesPrefix(), _ -> new ArrayList<>())
					.add(l.serviceName());
		}
		log.trace("{} inactive services :", inactive.size());
		for (EntityUpdater l : inactive) {
			log.trace(" {} : {}={}", l.serviceName(), l.propertiesPrefix(), l.propertiesAsString());

		}
		for (Entry<String, List<String>> e : propertiesPrefixToServices.entrySet()) {
			if (e.getValue().size() > 1) {
				log.error("propertyprefix {} is associated to {} services : {}", e.getKey(), e.getValue().size(), e.getValue());
			}
		}
	}

}
