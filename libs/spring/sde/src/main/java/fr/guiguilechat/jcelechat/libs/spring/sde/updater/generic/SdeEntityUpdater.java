package fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;

import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeListener;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public abstract class SdeEntityUpdater<Entity extends SdeEntity<Integer>, Service extends SdeEntityService<Entity, Integer, ?>, SdeSource>
		implements SdeListener {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private Service service;

	private final String fileName;

	private final JacksonYamlLHMLoader<SdeSource> loader;

	private boolean receivedFile = false;

	@Override
	public void beforeSdeUpdate() {
		receivedFile = false;
	}

	@Override
	public void onSdeFile(String entryName, Supplier<InputStream> fileContent) {
		if(entryName.equals(fileName)) {
			log.debug("{} processing file {}",
					getClass().getSimpleName(),
					fileName);
			long startTime = System.currentTimeMillis();
			service().setAllRemoved();
			var sources = loader.from(fileContent.get());
			processSource(sources);
			receivedFile = true;
			log.info("{} updated entities from {} entries in {} ms",
					getClass().getSimpleName(),
					sources.size(),
					System.currentTimeMillis() - startTime);
		}
	}

	protected abstract void processSource(LinkedHashMap<Integer, SdeSource> sources);

	@Override
	public void afterSdeUpdate() {
		if (!receivedFile) {
			log.warn("service " + getClass().getSimpleName() + " did not receive file "
					+ fileName);
		}
	}

}
