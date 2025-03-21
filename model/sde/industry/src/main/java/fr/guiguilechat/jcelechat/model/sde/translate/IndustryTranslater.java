package fr.guiguilechat.jcelechat.model.sde.translate;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import fr.guiguilechat.jcelechat.libs.gameclient.cache.ClientCache;
import fr.guiguilechat.jcelechat.libs.gameclient.meta.ClientInfo;
import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTime;
import fr.guiguilechat.jcelechat.libs.gameclient.structure.staticdata.EindustryActivities;
import fr.guiguilechat.jcelechat.libs.gameclient.structure.staticdata.EindustryActivityTargetFilters;
import fr.guiguilechat.jcelechat.model.sde.EveType;
import fr.guiguilechat.jcelechat.model.sde.TypeIndex;
import fr.guiguilechat.jcelechat.model.sde.TypeRef;
import fr.guiguilechat.jcelechat.model.sde.attributes.OreBasicType;
import fr.guiguilechat.jcelechat.model.sde.industry.Activity;
import fr.guiguilechat.jcelechat.model.sde.industry.Blueprint;
import fr.guiguilechat.jcelechat.model.sde.industry.IndustryUsage;
import fr.guiguilechat.jcelechat.model.sde.industry.InventionDecryptor;
import fr.guiguilechat.jcelechat.model.sde.industry.TargetFilter;
import fr.guiguilechat.jcelechat.model.sde.industry.activity.ArchivedActivityList;
import fr.guiguilechat.jcelechat.model.sde.industry.blueprint.ArchivedBlueprintList;
import fr.guiguilechat.jcelechat.model.sde.industry.targetfilter.ArchivedTargetFilterList;
import fr.guiguilechat.jcelechat.model.sde.types.Asteroid;
import fr.guiguilechat.jcelechat.model.sde.types.decryptors.GenericDecryptor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class IndustryTranslater {

	private static final Logger logger = LoggerFactory.getLogger(IndustryTranslater.class);

	/**
	 * @param args
	 *             should be [database destination root], typically
	 *             src/generated/resources/
	 * @throws IOException
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException, SQLException {

		long timeStart = System.currentTimeMillis();
		File folderOut = new File(args.length < 1 ? "src/generated/resources/" : args[0]);
		folderOut.mkdirs();

		ClientInfo ci = ClientInfo.fetch();
		ClientCache cc = new ClientCache(ci);
		LinkedHashMap<Integer, IndustryUsage> usages = new LinkedHashMap<>();

		List<Activity> activities = new ArrayList<>();
		translateActivities(cc, activities);
		File actFile = Activity.yaml().export(activities, folderOut);
		ArchivedActivityList.archiveOnDiff(actFile, ci.lastModified(), folderOut);

		LinkedHashMap<Integer, TargetFilter> filters = new LinkedHashMap<>();
		translateFilters(cc, filters);
		File filtersFile = TargetFilter.export(filters, folderOut);
		ArchivedTargetFilterList.archiveOnDiff(filtersFile, ci.lastModified(), folderOut);

		LinkedHashMap<Integer, Blueprint> blueprints = new LinkedHashMap<>();
		new ClientCacheBlueprintTranslator(cc).translateBlueprints(cc, blueprints, usages);
//		new SDEBlueprintTranslator().translateBlueprints(blueprints, usages);
		LinkedHashMap<Integer, InventionDecryptor> decryptors = new LinkedHashMap<>();
		translateDecryptors(decryptors);
		// sort decryptors and blueprints by typeId
		Stream.of(blueprints, decryptors).forEach(m -> {
			ArrayList<Entry<Integer, ? extends TypeRef<?>>> list = new ArrayList<>(m.entrySet());
			Collections.sort(list, Comparator.comparing(Entry<Integer, ? extends TypeRef<?>>::getKey));
			m.clear();
			for (Entry<Integer, ? extends TypeRef<?>> e : list) {
				((Map<Integer, TypeRef<?>>) m).put(e.getKey(), e.getValue());
			}
		});
		File bpFile = Blueprint.yaml().export(blueprints, folderOut);
		ArchivedBlueprintList.archiveOnDiff(bpFile, ci.lastModified(), folderOut);
		InventionDecryptor.export(decryptors, folderOut);

		translateCompression(usages);
		IndustryUsage.export(usages, folderOut);

		log.info("exported industry in " + (System.currentTimeMillis() - timeStart) / 1000 + "s");

	}

	//
	// decryptor translation from sde
	//

	static void translateDecryptors(LinkedHashMap<Integer, InventionDecryptor> decryptors) {
		for (Entry<Integer, GenericDecryptor> e : GenericDecryptor.METAGROUP.load().entrySet()) {
			decryptors.put(e.getKey(), convertDecryptor(e.getValue()));
		}
		InventionDecryptor nullDecryptor = new InventionDecryptor();
		decryptors.put(0, nullDecryptor);
	}

	static InventionDecryptor convertDecryptor(GenericDecryptor dec) {
		InventionDecryptor ret = new InventionDecryptor();
		ret.id = dec.id;
		return ret;
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
						usages.computeIfAbsent(compressed.id, o -> new IndustryUsage()).compressTo = compressIntoId;
						usages.computeIfAbsent(compressIntoId, o -> new IndustryUsage()).compressFrom = compressed.id;
					}
				}
			}
		}
	}

	static void translateActivities(ClientCache cc, List<Activity> activities)
			throws JsonMappingException, JsonProcessingException, SQLException {
		List<KeyValTime<EindustryActivities>> loaded = EindustryActivities.getLoader().load(cc);
		loaded.stream()
				.map(KeyValTime::getVal)
				.sorted(Comparator.comparing(eia -> eia.activityID))
				.map(eia -> new Activity(
						eia.activityID,
						eia.activityName,
						eia.description))
				.forEach(activities::add);
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
}
