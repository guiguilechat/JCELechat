package fr.guiguilechat.jcelechat.model.sde.industry;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import fr.guiguilechat.jcelechat.libs.exports.common.ArchiveManager;
import fr.guiguilechat.jcelechat.libs.exports.common.MapIntSerializer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class TargetFilter {

	//
	// storage
	//

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final MapIntSerializer<TargetFilter> storage = new MapIntSerializer<>(
			"SDE/industry/targetfilters.yaml",
			TargetFilter.class);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final ArchiveManager<Map<Integer, TargetFilter>> archives = new ArchiveManager<>(
			"SDE/industry/targetfilters/",
			storage()::load);

	/**
	 * load the archived list for given date.
	 */
	public static Map<Integer, TargetFilter> load(Instant date) {
		return archives().dichoSearch(date, storage().load());
	}

	// only warn about missing ids once
	private static Set<Integer> missingIds = Collections.synchronizedSet(new HashSet<>());

	public static TargetFilter of(int id, Instant date) {
		TargetFilter ret = (date == null ? storage().load() : load(date)).get(id);
		if (ret == null && missingIds.add(id)) {
			log.warn("unknown id " + id);
		}
		return ret;
	}

	public static TargetFilter of(int id) {
		return of(id, null);
	}

	//
	// structure
	//

	public int id;
	public List<Integer> categoryIds = new ArrayList<>();
	public List<Integer> groupIds = new ArrayList<>();
	public String name;

}
