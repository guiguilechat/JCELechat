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
import fr.guiguilechat.jcelechat.libs.sde.cache.yaml.YamlLoader;

public class TestAllParsers {

	public static Class<?>[] parsers =
			{
					EaccountingEntryTypes.class,
					EagentsInSpace.class,
					EagentTypes.class,
					Eancestries.class,
					EappliedProximityEffects.class,
					Earchetypes.class,
					Ebloodlines.class,
					Eblueprints.class,
					Ecategories.class,
					Ecertificates.class,
					EcharacterAttributes.class,
					EcharacterTitles.class,
					EcloneGrades.class,
					EcompressibleTypes.class,
					EcontrabandTypes.class,
					EcontrolTowerResources.class,
					EcorporationActivities.class,
					EcorporationRoleGroups.class,
					EcorporationRoles.class,
					EdbuffCollections.class,
					EdogmaAttributeCategories.class,
					EdogmaAttributes.class,
					EdogmaEffects.class,
					EdogmaUnits.class,
					Edungeons.class,
					EdynamicItemAttributes.class,
					EepicArcs.class,
					EexpertSystems.class,
					Efactions.class,
					EfighterAbilities.class,
					EfighterAbilitiesByType.class,
					EfreelanceJobSchemas.class,
					EgraphicMaterialSets.class,
					Egraphics.class,
					Egroups.class,
					Eicons.class,
					EindustryActivities.class,
					EindustryAssemblyLines.class,
					EindustryInstallationTypes.class,
					EindustryModifierSources.class,
					EindustryTargetFilters.class,
					Elandmarks.class,
					ElinkWithShip.class,
					EmapAsteroidBelts.class,
					EmapConstellations.class,
					EmapMoons.class,
					EmapPlanets.class,
					EmapRegions.class,
					EmapSecondarySuns.class,
					EmapSolarSystems.class,
					EmapStargates.class,
					EmapStars.class,
					EmarketGroups.class,
					Emasteries.class,
					EmercenaryTacticalOperations.class,
					EmetaGroups.class,
					EmetenoxMoonDrill.class,
					EmilitaryCampaignObjectives.class,
					EmilitaryCampaigns.class,
					Emissions.class,
					EnotificationTypes.class,
					EnpcCharacters.class,
					EnpcCorporationDivisions.class,
					EnpcCorporations.class,
					EnpcStations.class,
					EplanetResources.class,
					EplanetSchematics.class,
					EproximityTrap.class,
					Eraces.class,
					EschoolMap.class,
					Eschools.class,
					EshipTreeElements.class,
					EshipTreeFactions.class,
					EshipTreeGroups.class,
					EskillPlans.class,
					EskinLicenses.class,
					EskinMaterials.class,
					EskinrComponentCategories.class,
					EskinrComponentPointValues.class,
					EskinrComponentRarities.class,
					EskinrComponents.class,
					EskinrSlotCategories.class,
					EskinrSlotConfigurations.class,
					EskinrSlotNames.class,
					EskinrSlots.class,
					EskinrSlotsToMaterials.class,
					EskinrTierThresholds.class,
					Eskins.class,
					EsovereigntyUpgrades.class,
					EstationOperations.class,
					EstationServices.class,
					EstationStandingsRestrictions.class,
					EsystemDbuffEmitters.class,
					EsystemWideEffects.class,
					EtranslationLanguages.class,
					EtypeBonus.class,
					EtypeDogma.class,
					EtypeElements.class,
					EtypeLists.class,
					EtypeMaterials.class,
					Etypes.class
			};

	public static void main(String[] args)
			throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
		DLResult res = YamlCache.INSTANCE.donwloadSDE();
		System.out.println("dl : " + res);

		// remove the _sde directory
		int nbFiles =
				YamlCache.INSTANCE.extractCacheDir().listFiles().length
						- 1 // ignore _sde
		;
		int missingParsersNb =
				nbFiles
						- parsers.length;
		if (missingParsersNb != 0) {
			System.err.println("received " + nbFiles + " parseable files, with " + parsers.length + " parsers, missing "
					+ missingParsersNb + " parsers");
		}
		Map<String, Set<String>> multipleClasses = new LinkedHashMap<>();
		for (Class<?> cl : parsers) {
			long start = System.currentTimeMillis();
			Field f = cl.getField("LOADER");
			@SuppressWarnings({ "rawtypes", "unchecked" })
			YamlLoader<Map<?, ?>> loader = ((MapLoader) f.get(null)).yaml();

			System.out.println("\n" + loader.getArchiveFileName());
			Method m = cl.getMethod("main", String[].class);
			m.invoke(null, new Object[] { null });// need to add param otherwise call exception
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
