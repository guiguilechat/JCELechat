package fr.guiguilechat.jcelechat.libs.spring.industry.reprocess;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeUpdateListener;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeMaterials;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EtypeMaterials.Material;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ReprocessUpdater implements SdeUpdateListener {

	private final TypeService typeService;

	private final ReprocessService reprocessService;

	private boolean sdeFileMissing = true;

	@Override
	public void beforeSdeUpdate() {
		sdeFileMissing = true;
		reprocessService.clear();
	}

	static final Pattern ENTRYNAME_TYPEMATERIAL_PATTERN = Pattern.compile(
	    "fsd/typeMaterials\\.yaml");

	@Override
	public void onSdeFile(String name, Supplier<InputStream> fileContent) {
		if (ENTRYNAME_TYPEMATERIAL_PATTERN.matcher(name).matches()) {
			saveReprocess(fileContent.get());
			return;
		}
	}

	protected void saveReprocess(InputStream inputStream) {
		sdeFileMissing = false;
		LinkedHashMap<Integer, EtypeMaterials> reprocesses = EtypeMaterials.from(inputStream);
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
				created.add(Reprocess.builder()
				    .product(idToType.get(m.materialTypeID))
				    .quantity(m.quantity)
				    .reprocessed(reprocessed)
				    .build());
			}
		}
		reprocessService.saveAll(created);
	}

	@Override
	public void afterSdeUpdate() {
		if (sdeFileMissing) {
			log.warn("service " + getClass().getSimpleName() + " did not receive file for matcher "
			    + ENTRYNAME_TYPEMATERIAL_PATTERN);
		}
	}

}
