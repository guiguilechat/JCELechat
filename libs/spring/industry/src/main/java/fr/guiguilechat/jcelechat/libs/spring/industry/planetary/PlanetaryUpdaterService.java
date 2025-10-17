package fr.guiguilechat.jcelechat.libs.spring.industry.planetary;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.EplanetSchematics;
import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SdeListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PlanetaryUpdaterService implements SdeListener {

	final private SchematicService schematicService;

	final private SchemMaterialService schemMaterialService;

	final private SchemProductService schemProductService;

	private final TypeService typeService;

	private boolean sdeFileMissing = true;

	@Override
	public void beforeSdeUpdate() {
		sdeFileMissing = true;
		schemProductService.clear();
		schemMaterialService.clear();
		schematicService.clear();
	}

	@Override
	public void onSdeFile(String entryName, Supplier<InputStream> fileContent) {
		if (entryName.equals(EplanetSchematics.SDE_FILE_YAML)) {
			saveSchematics(fileContent.get());
		}
	}

	private void saveSchematics(InputStream is) {
		sdeFileMissing = false;
		Map<Integer, EplanetSchematics> planetSchematics = new HashMap<>(EplanetSchematics.LOADER.from(is));

		Map<Integer, Type> typesById = typeService.createIfAbsent(
		    planetSchematics.entrySet().stream()
		        .flatMap(e -> Stream.concat(Stream.of(e.getKey()),
		            Stream.concat(e.getValue().pins.stream(), e.getValue().types.keySet().stream())))
		        .distinct().toList());

		List<Schematic> saved = schematicService
		    .saveAll(planetSchematics.entrySet().stream().map(e -> {
			    Schematic ret = Schematic.of(e.getValue(), e.getKey());
			    List<Integer> pins = e.getValue().pins;
			    if (pins != null && !pins.isEmpty()) {
				    ret.setPins(new HashSet<>(pins.stream().map(typesById::get).toList()));
			    }
			    ret.setMaterials(e.getValue().types.entrySet().stream()
			        .filter(entry -> entry.getValue().isInput)
			        .map(entry -> SchemMaterial.builder()
			            .schematic(ret)
			            .quantity(entry.getValue().quantity)
			            .type(typesById.get(entry.getKey()))
			            .build())
			        .toList());
			    ret.setProducts(e.getValue().types.entrySet().stream()
			        .filter(entry -> !entry.getValue().isInput)
			        .map(entry -> SchemProduct.builder()
			            .schematic(ret)
			            .quantity(entry.getValue().quantity)
			            .type(typesById.get(entry.getKey()))
			            .build())
			        .toList());
			    return ret;
		    }).toList());
		log.info("updated {} schematics with {} used types", saved.size(), typesById.size());
	}

	@Override
	public void afterSdeUpdate() {
		if (sdeFileMissing) {
			log.warn("service " + getClass().getSimpleName() + " did not receive file "
					+ EplanetSchematics.SDE_FILE_YAML);
		}
	}

}
