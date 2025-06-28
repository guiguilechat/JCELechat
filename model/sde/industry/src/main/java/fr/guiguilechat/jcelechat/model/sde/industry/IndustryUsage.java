package fr.guiguilechat.jcelechat.model.sde.industry;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.exports.common.MapIntSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

/** The bp names a type is used into, for each activity. */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class IndustryUsage {

	//
	// storage
	//

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final MapIntSerializer<IndustryUsage> storage = new MapIntSerializer<>("SDE/industry/usages.yaml",
			IndustryUsage.class);

	// only warn about missing ids once
	private static final Set<Integer> missingIds = new HashSet<>();

	// missing ids that have not been printed on debug yet
	private static final Set<Integer> newMissings = new HashSet<>();

	public static IndustryUsage of(int id) {
		IndustryUsage ret = storage().load().get(id);
		if (ret == null) {
			synchronized (missingIds) {
				if (missingIds.add(id)) {
					newMissings.add(id);
				}
			}
		}
		return ret;
	}

	public static void debugMissings() {
		List<Integer> ids = new ArrayList<>();
		synchronized (missingIds) {
			ids.addAll(newMissings);
			newMissings.clear();
		}
		ids.sort(Integer::compareTo);
		log.debug("unknown industry usage of ids " + ids);
	}

	public static LinkedHashMap<Integer, IndustryUsage> load() {
		return storage().load();
	}

	// structure

	public LinkedHashSet<Integer> materialInCopy = new LinkedHashSet<>();

	public LinkedHashSet<Integer> materialInInvention = new LinkedHashSet<>();

	public LinkedHashSet<Integer> productOfInvention = new LinkedHashSet<>();

	public LinkedHashSet<Integer> materialInManuf = new LinkedHashSet<>();

	public LinkedHashSet<Integer> productOfManuf = new LinkedHashSet<>();

	public LinkedHashSet<Integer> materialInME = new LinkedHashSet<>();

	public LinkedHashSet<Integer> materialInTE = new LinkedHashSet<>();

	public LinkedHashSet<Integer> materialInReaction = new LinkedHashSet<>();

	public LinkedHashSet<Integer> productOfReaction = new LinkedHashSet<>();

	/**
	 * map of the type ids to their quantities that are resulting of reprocessing
	 * this.Quantities are the average for one item.
	 */
	public LinkedHashMap<Integer, Double> reprocessInto = new LinkedHashMap<>();

	/**
	 * ids of the types that can be reprocessed into this
	 */
	public LinkedHashSet<Integer> reprocessedFrom = new LinkedHashSet<>();

	public int compressTo = 0;

	public int compressFrom = 0;

}
