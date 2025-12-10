package fr.guiguilechat.jcelechat.libs.spring.sde.industry.reprocess;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EtypeMaterials;
import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EtypeMaterials.Material;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.sde.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ReprocessUpdater implements SdeListener {

	private final TypeService typeService;

	private final ReprocessRepository reprocessRepository;

	private boolean sdeFileMissing = true;

	@Override
	public void beforeSdeUpdate() {
		sdeFileMissing = true;
	}

	@Override
	public void onSdeFile(String name, Supplier<InputStream> fileContent) {
		if (name.equals(EtypeMaterials.LOADER.yamlFileName())) {
			sdeFileMissing = false;
			processSource(fileContent.get());
			return;
		}
	}

	protected void processSource(InputStream fileContent) {
		reprocessRepository.delete();
		LinkedHashMap<Integer, EtypeMaterials> reprocesses = EtypeMaterials.LOADER.yaml().from(fileContent);
		Map<Integer, Type> idToType = typeService.createIfAbsent(reprocesses.entrySet().stream()
		    .flatMap(
		        e -> Stream.concat(Stream.of(e.getKey()),
		            e.getValue().materials.stream().map(mat -> mat.materialTypeID)))
		    .distinct()
		    .toList());
		List<Reprocess> created = new ArrayList<>();
		for (Entry<Integer, EtypeMaterials> e : reprocesses.entrySet()) {
			Type reprocessed = idToType.get(e.getKey());
			for (Material m : e.getValue().materials) {
				created.add(Reprocess.of(reprocessed, idToType.get(m.materialTypeID), m.quantity));
			}
		}
		Map<Long, List<Reprocess>> gr = created.stream().collect(Collectors.groupingBy(Reprocess::getId));
		for (Entry<Long, List<Reprocess>> e : gr.entrySet()) {
			if (e.getValue().size() > 1) {
				log.error("error : same id={} for {} #reprocess, first is {} ",
						e.getKey(),
						e.getValue().size(),
						e.getValue().get(0));
			}
		}
		reprocessRepository.saveAllAndFlush(created);
	}

	@Override
	public void afterSdeUpdate() {
		if (sdeFileMissing) {
			log.warn("service " + getClass().getSimpleName() + " did not receive file "
					+ EtypeMaterials.LOADER.yamlFileName());
		}
	}

}
