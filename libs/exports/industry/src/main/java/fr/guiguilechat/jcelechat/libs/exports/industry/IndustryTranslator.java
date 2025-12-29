package fr.guiguilechat.jcelechat.libs.exports.industry;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.guiguilechat.jcelechat.libs.exports.industry.translate.ActivityModifierSourceTranslator;
import fr.guiguilechat.jcelechat.libs.exports.industry.translate.BlueprintTranslator;
import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ClientInfo;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTime;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.EindustryActivities;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.EindustryActivityTargetFilters;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata.EindustryInstallationTypes;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.attributes.OreBasicType;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import fr.guiguilechat.jcelechat.model.sde.types.decryptors.GenericDecryptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndustryTranslator {

	private static final Logger logger = LoggerFactory.getLogger(IndustryTranslator.class);

	/**
	 * @param args
	 *             should be [database destination root], typically
	 *             src/generated/resources/
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void main(String[] args) throws IOException, SQLException {

		long timeStart = System.currentTimeMillis();
		File folderOut = new File(args.length < 1 ? "src/generated/resources/" : args[0]);
		folderOut.mkdirs();

		ClientInfo ci = ClientInfo.fetch();
		ClientCache cc = new ClientCache(ci);
		LinkedHashMap<Integer, IndustryUsage> usages = new LinkedHashMap<>();

		LinkedHashMap<Integer, Activity> activities = new LinkedHashMap<>();
		translateActivities(cc, activities);
		File actFile = Activity.storage().export(activities, folderOut);
		Activity.archives().archiveOnDiff(actFile, ci.lastModified(), folderOut);

		LinkedHashMap<Integer, TargetFilter> filters = new LinkedHashMap<>();
		translateFilters(cc, filters);
		File filtersFile = TargetFilter.storage().export(filters, folderOut);
		TargetFilter.archives().archiveOnDiff(filtersFile, ci.lastModified(), folderOut);

		LinkedHashMap<Integer, ActivityModifierSource> activityModifierSources = new LinkedHashMap<>();
		new ActivityModifierSourceTranslator().translate(cc, activityModifierSources);
		File amsFile = ActivityModifierSource.storage().export(activityModifierSources, folderOut);
		ActivityModifierSource.archives().archiveOnDiff(amsFile, ci.lastModified(), folderOut);

		LinkedHashMap<Integer, InstallationType> installationTypes = new LinkedHashMap<>();
		translateInstallationTypes(cc, installationTypes);
		File itFile = InstallationType.storage().export(installationTypes, folderOut);
		InstallationType.archives().archiveOnDiff(itFile, ci.lastModified(), folderOut);

		LinkedHashMap<Integer, InventionDecryptor> decryptors = new LinkedHashMap<>();
		translateDecryptors(decryptors);
		File idFile = InventionDecryptor.storage().export(decryptors, folderOut);
		InventionDecryptor.archives().archiveOnDiff(idFile, ci.lastModified(), folderOut);

		LinkedHashMap<Integer, Blueprint> blueprints = new LinkedHashMap<>();
		new BlueprintTranslator(cc).translateBlueprints(cc, blueprints, usages);
		// sort blueprints by typeId
		ArrayList<Entry<Integer, Blueprint>> sortedList = new ArrayList<>(blueprints.entrySet());
		Collections.sort(sortedList, Comparator.comparing(Entry<Integer, Blueprint>::getKey));
		blueprints.clear();
		for (Entry<Integer, Blueprint> e : sortedList) {
			blueprints.put(e.getKey(), e.getValue());
		}
		File bpFile = Blueprint.storage().export(blueprints, folderOut);
		Blueprint.archives().archiveOnDiff(bpFile, ci.lastModified(), folderOut);

		translateCompression(usages);
		IndustryUsage.storage().export(usages, folderOut);

		log.info("exported industry in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");

	}

	static void translateDecryptors(LinkedHashMap<Integer, InventionDecryptor> decryptors) {
		decryptors.put(0, InventionDecryptor.NULLDECRYPTOR);
		GenericDecryptor.METAGROUP.load().entrySet().stream()
				.sorted(Comparator.comparing(Entry::getKey))
				.map(e -> convertDecryptor(e.getValue()))
				.forEach(id -> decryptors.put(id.typeId, id));
	}

	static InventionDecryptor convertDecryptor(GenericDecryptor dec) {
		return new InventionDecryptor(dec.id,
				dec.name,
				(int) dec.inventionmaxrunmodifier,
				(int) dec.inventionmemodifier,
				(int) dec.inventiontemodifier,
				dec.inventionpropabilitymultiplier);
	}

	static void translateCompression(LinkedHashMap<Integer, IndustryUsage> usages) {
		for (Asteroid compressed : Asteroid.METACAT.load().values()) {
			if (compressed.getAttributes().contains(OreBasicType.INSTANCE) && !compressed.name.startsWith("Batch ")) {
				Integer compressIntoId = compressed.valueSet(OreBasicType.INSTANCE).intValue();
				if (compressIntoId != 0 && compressIntoId != compressed.id) {
					EveType compressInto = TypeIndex.getType(compressIntoId);
					if (compressInto == null) {
						logger.debug("can't find asteroid from id " + compressIntoId);
					} else {
						usages.computeIfAbsent(compressed.id, _ -> new IndustryUsage()).compressTo = compressIntoId;
						usages.computeIfAbsent(compressIntoId, _ -> new IndustryUsage()).compressFrom = compressed.id;
					}
				}
			}
		}
	}

	static void translateActivities(ClientCache cc, LinkedHashMap<Integer, Activity> activities)
			throws JsonMappingException, JsonProcessingException, SQLException {
		List<KeyValTime<EindustryActivities>> loaded = EindustryActivities.getLoader().load(cc);
		loaded.stream()
				.map(KeyValTime::getVal)
				.sorted(Comparator.comparing(eia -> eia.activityID))
				.map(eia -> new Activity(
						eia.activityID,
						eia.activityName,
						eia.description))
				.forEach(act -> activities.put(act.activityId, act));
	}

	static void translateFilters(ClientCache cc, LinkedHashMap<Integer, TargetFilter> filters)
			throws JsonMappingException, JsonProcessingException, SQLException {
		List<KeyValTime<EindustryActivityTargetFilters>> loaded = EindustryActivityTargetFilters.getLoader().load(cc);
		loaded.stream()
				.sorted(Comparator.comparing(KeyValTime::getKey))
				.map(kv -> new TargetFilter(
						kv.getKey(),
						kv.getVal().categoryIDs == null ? null : kv.getVal().categoryIDs.stream().sorted().toList(),
						kv.getVal().groupIDs == null ? null : kv.getVal().groupIDs.stream().sorted().toList(),
						kv.getVal().name))
				.forEach(f -> filters.put(f.id, f));
	}

	static void translateInstallationTypes(ClientCache cc, LinkedHashMap<Integer, InstallationType> newMap)
			throws JsonMappingException, JsonProcessingException, SQLException {
		List<KeyValTime<EindustryInstallationTypes>> loaded = EindustryInstallationTypes.getLoader().load(cc);
		loaded.stream()
				.sorted(Comparator.comparing(kvt -> kvt.getVal().typeId))
				.map(kv -> new InstallationType(
						kv.getVal().typeId,
						new ArrayList<>(kv.getVal().assembly_lines.stream().map(al -> al.assemblyLine).toList())))
				.forEach(f -> newMap.put(f.typeId, f));
	}
}
