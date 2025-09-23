package fr.guiguilechat.jcelechat.model.sde2;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import fr.guiguilechat.jcelechat.model.sde2.parsers.*;
import fr.guiguilechat.jcelechat.model.sde2.yaml.JacksonYamlLoader;
import fr.guiguilechat.jcelechat.model.sde2.yaml.YamlCache;

public class TestAllParsers {

	public static void main(String[] args)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
		YamlCache.INSTANCE.donwloadSDE();
		for (Class<?> cl : new Class<?>[] {
				Eagents.class,
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
				Egraphics.class,
				Egroups.class,
				// icons,
				// landmarks
				EmapAsteroidBelts.class,
				EmapConstellations.class,
				EmapMoons.class,
				EmapPlanets.class,
				EmapRegions.class,
				EmapSolarSystems.class,
				EmapStargates.class,
				EmapStars.class,
				EmarketGroups.class,
				// masteries,
				// metagroup,
				// npccorporationdivisions,
				EnpcCorporations.class,
				// npcstations,
				// planet resources,
				EplanetSchematics.class,
				// races,
				// research agents,
				// skin licenses,
				// skin materials,
				// skins,
				// sovereignty upgrades,
				EstationOperations.class,
				EstationServices.class,
				// translation languages
				EtypeBonus.class,
				EtypeDogma.class,
				EtypeMaterials.class,
				Etypes.class
		}) {
			long start = System.currentTimeMillis();
			Field f = cl.getField("LOADER");
			JacksonYamlLoader<?> loader = (JacksonYamlLoader<?>) f.get(null);

			System.out.println("\n" + loader.getArchiveFileName());
			Method m = cl.getMethod("main", String[].class);
			m.invoke(null, new Object[] { null });
			System.out.println(" in " + (System.currentTimeMillis() - start) + "ms");
		}

	}

}
