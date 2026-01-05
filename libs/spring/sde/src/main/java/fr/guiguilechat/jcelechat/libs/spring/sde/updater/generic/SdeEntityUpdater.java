package fr.guiguilechat.jcelechat.libs.spring.sde.updater.generic;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;

import fr.guiguilechat.jcelechat.libs.sde.cache.IntMapLoader;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeListener;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public abstract class SdeEntityUpdater<Entity extends SdeEntity<Integer>, Repo extends SdeEntityRepository<Entity, Integer>, Service extends SdeEntityService<Entity, Integer, Repo>, SdeSource>
		implements SdeListener {

	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private Repo repo;

	/** required to create */
	@Autowired // can't use constructor injection for generic service
	@Accessors(fluent = true)
	@Getter(value = AccessLevel.PROTECTED)
	private Service service;

	private final IntMapLoader<SdeSource> loader;

	private boolean receivedFile = false;

	@Getter
	@Setter
	protected boolean skip = false;

	@Override
	public void beforeSdeUpdate() {
		if (skip) {
			return;
		}
		receivedFile = false;
	}

	@Override
	public void onSdeFile(String entryName, Supplier<InputStream> fileContent) {
		if (skip) {
			return;
		}
		if (entryName.equals(loader.yamlFileName())) {
			log.debug("{} processing file {}",
					getClass().getSimpleName(),
					loader.yamlFileName());
			long startTime = System.currentTimeMillis();
			repo().setAllRemoved();
			var sources = loader.yaml().from(fileContent.get());
			processSource(sources);
			receivedFile = true;
			log.info(" {} processed by {}, {} entries in {} ms",
					loader.yamlFileName(),
					getClass().getSimpleName(),
					sources.size(),
					System.currentTimeMillis() - startTime);
		}
	}

	protected abstract void processSource(LinkedHashMap<Integer, SdeSource> sources);

	@Override
	public void afterSdeUpdate() {
		if (skip) {
			return;
		}
		if (!receivedFile) {
			log.warn("service {} did not receive file {}",
					getClass().getSimpleName(),
					loader.yamlFileName());
		}
		List<Entity> notReceived = repo().findAllByReceivedFalse();
		if (!notReceived.isEmpty()) {
			log.warn("service {} has {} non received entities with ids {}",
					getClass().getSimpleName(),
					notReceived.size(),
					notReceived.stream().map(SdeEntity::getId).toList());
		}
	}

}
