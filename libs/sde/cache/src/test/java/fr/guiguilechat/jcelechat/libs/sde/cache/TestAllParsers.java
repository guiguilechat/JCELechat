package fr.guiguilechat.jcelechat.libs.sde.cache;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.libs.sde.cache.parsers.*;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.DLResult;
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.JacksonYamlLHMLoader;

public class TestAllParsers {

	public static void main(String[] args)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
		DLResult res = YamlCache.INSTANCE.donwloadSDE();
		System.out.println("dl : " + res);
		Map<String, Set<String>> multipleClasses = new LinkedHashMap<>();
		for (Class<?> cl : new Class<?>[] {
				EagentsInSpace.class,
				EagentTypes.class,
				Eancestries.class,
				Ebloodlines.class,
				Eblueprints.class,
				Ecategories.class,
				Ecertificates.class,
				EcharacterAttributes.class,
				EcontrabandTypes.class,
				EcontrolTowerResources.class,
				EcorporationActivities.class,
				EdbuffCollections.class,
				EdogmaAttributeCategories.class,
				EdogmaAttributes.class,
				EdogmaEffects.class,
				EdogmaUnits.class,
				EdynamicItemAttributes.class,
				Efactions.class,
				EfreelanceJobSchemas.class,
				Egraphics.class,
				Egroups.class,
				Eicons.class,
				Elandmarks.class,
				EmapAsteroidBelts.class,
				EmapConstellations.class,
				EmapMoons.class,
				EmapPlanets.class,
				EmapRegions.class,
				EmapSolarSystems.class,
				EmapStargates.class,
				EmapStars.class,
				EmarketGroups.class,
				Emasteries.class,
				EmetaGroups.class,
				EnpcCharacters.class,
				EnpcCorporationDivisions.class,
				EnpcCorporations.class,
				EnpcStations.class,
				EplanetResources.class,
				EplanetSchematics.class,
				Eraces.class,
				EskinLicenses.class,
				EskinMaterials.class,
				Eskins.class,
				EsovereigntyUpgrades.class,
				EstationOperations.class,
				EstationServices.class,
				// translation languages // won't do, indexed by string key
				EtypeBonus.class,
				EtypeDogma.class,
				EtypeMaterials.class,
				Etypes.class
		}) {
			long start = System.currentTimeMillis();
			Field f = cl.getField("LOADER");
			JacksonYamlLHMLoader<?> loader = ((IntMapLoader<?>) f.get(null)).yaml();

			System.out.println("\n" + loader.getArchiveFileName());
			Method m = cl.getMethod("main", String[].class);
			m.invoke(null, new Object[] { null });
			System.out.println(" in " + (System.currentTimeMillis() - start) + "ms");
			var classes = loader.load().values().stream().map(v -> v.getClass().getSimpleName())
					.collect(Collectors.toSet());
			if (classes.size() > 1) {
				multipleClasses.put(loader.getArchiveFileName(), classes);
			}
		}

		if (!multipleClasses.isEmpty()) {
			System.err.println("invalid parsed classes for files :");
			for (Entry<String, Set<String>> e : multipleClasses.entrySet()) {
				System.err.println(e.getKey() + e.getValue());
			}
		}
	}

}
