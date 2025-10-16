package fr.guiguilechat.jcelechat.libs.spring.universe.region;

import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.function.Supplier;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EmapRegions;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdateListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@Slf4j
public class RegionUpdaterService implements SdeUpdateListener {

	@Lazy
	private final RegionService regionService;

	private boolean receivedRegions = false;

	@Override
	public void beforeSdeUpdate() {
		receivedRegions = false;
	}

	@Override
	public void onSdeFile(String entryName, Supplier<InputStream> fileContent) {
		switch (entryName) {
		case EmapRegions.SDE_FILE_YAML:
			processRegions(EmapRegions.LOADER.from(fileContent.get()));
			receivedRegions = true;
			break;
		}
	}

	protected void processRegions(LinkedHashMap<Integer, EmapRegions> from) {
		var storedEntities = new HashMap<>(regionService.allById());
		storedEntities.values().forEach(o -> o.setRemoved(true));

		for (var e : from.entrySet()) {
			var stored = storedEntities.computeIfAbsent(e.getKey(), i -> Region.builder().id(i).build());
			stored.update(e.getValue());
		}
		regionService.saveAll(storedEntities.values());
	}

	@Override
	public void afterSdeUpdate() {
		if (!receivedRegions) {
			log.warn("service " + getClass().getSimpleName() + " did not receive file "
					+ EmapRegions.SDE_FILE_YAML);
		}
	}

}
