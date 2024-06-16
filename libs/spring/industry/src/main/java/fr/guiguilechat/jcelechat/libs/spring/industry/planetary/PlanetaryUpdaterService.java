package fr.guiguilechat.jcelechat.libs.spring.industry.planetary;

import java.io.InputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.regex.Pattern;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import fr.guiguilechat.jcelechat.libs.spring.items.type.Type;
import fr.guiguilechat.jcelechat.libs.spring.items.type.TypeService;
import fr.guiguilechat.jcelechat.libs.spring.sde.updater.SDEUpdateService.SdeUpdateListener;
import fr.guiguilechat.jcelechat.model.sde.load.fsd.EplanetSchematics;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PlanetaryUpdaterService implements SdeUpdateListener {

	final private SchematicService schematicService;

	final private SchemMaterialService schemMaterialService;

	final private SchemProductService schemProductService;

	private final TypeService typeService;

	@Override
	public void beforeSdeUpdate() {
		schemProductService.clear();
		schemMaterialService.clear();
		schematicService.clear();
	}

	static final Pattern ENTRYNAME_PLANETSCHEMATICS_PATTERN = Pattern.compile(
	    "fsd/planetSchematics\\.yaml");

	@Override
	public void onSdeFile(String entryName, Supplier<InputStream> fileContent) {
		if (ENTRYNAME_PLANETSCHEMATICS_PATTERN.matcher(entryName).matches()) {
			saveSchematics(fileContent.get());
		}
	}

	private void saveSchematics(InputStream is) {
		Map<Integer, EplanetSchematics> planetSchematics = new HashMap<>(EplanetSchematics.from(is));

		Map<Integer, Type> typesById = typeService.allById();

		schematicService
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
	}

}
