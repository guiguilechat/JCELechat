package fr.guiguilechat.jcelechat.libs.spring.update.dump;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.update.manager.IEntityUpdater;
import fr.guiguilechat.jcelechat.libs.spring.update.manager.ResourceUpdaterService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class Manager {

	private final ResourceUpdaterService s;

	@PostConstruct
	protected void debugConfig() {
		log.trace("configuration of {} registered updaters, with manager skip={} defaultSkip={} updatedDelay={}",
		    s.getFetchedServices().orElse(List.of()).size(), s.isSkip(), s.isDefaultSkip(), s.getUpdatedDelay());
		Map<String, List<String>> propertiesPrefixToServices = new HashMap<>();
		log.trace("{} active services :",
		    s.getFetchedServices().orElse(List.of()).stream().filter(l -> !s.skipService(l)).count());
		List<IEntityUpdater> inactive = new ArrayList<>();
		for (IEntityUpdater l : s.getFetchedServices().orElse(List.of())) {
			if (s.skipService(l)) {
				inactive.add(l);
			} else {
				log.trace(" {} : {}={}", l.fetcherName(), l.propertiesPrefix(), l.propertiesAsString());
			}
			propertiesPrefixToServices.computeIfAbsent(l.propertiesPrefix(), s -> new ArrayList<>()).add(l.fetcherName());
		}
		log.trace("{} inactive services :", inactive.size());
		for (IEntityUpdater l : inactive) {
			log.trace(" {} : {}={}", l.fetcherName(), l.propertiesPrefix(), l.propertiesAsString());

		}
		for (Entry<String, List<String>> e : propertiesPrefixToServices.entrySet()) {
			if (e.getValue().size() > 1) {
				log.error("propertyprefix {} is associated to {} services : {}", e.getKey(), e.getValue().size(), e.getValue());
			}
		}
	}

}
