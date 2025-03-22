package fr.guiguilechat.jcelechat.model.sde.industry;

import java.time.Instant;
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
@AllArgsConstructor
@NoArgsConstructor
public class ActivityModifierSource {

	//
	// storage
	//

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final MapIntSerializer<ActivityModifierSource> storage = new MapIntSerializer<>(
			"SDE/industry/activitymodifiersources.yaml",
			ActivityModifierSource.class);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final ArchiveManager<Map<Integer, ActivityModifierSource>> archives = new ArchiveManager<>(
			"SDE/industry/activitymodifiersources/",
			storage()::load);

	/**
	 * load the archived list for given date.
	 */
	public static Map<Integer, ActivityModifierSource> load(Instant date) {
		return archives().dichoSearch(date, storage().load());
	}

	// only warn about missing ids once
	private static Set<Integer> missingIds = Collections.synchronizedSet(new HashSet<>());

	public static ActivityModifierSource of(int id, Instant date) {
		ActivityModifierSource ret = (date == null ? storage().load() : load(date)).get(id);
		if (ret == null && missingIds.add(id)) {
			log.warn("unknown id " + id);
		}
		return ret;
	}

	public static ActivityModifierSource of(int id) {
		return of(id, null);
	}

	//
	// structure
	//

	@AllArgsConstructor
	@NoArgsConstructor
	public static class ModifiedActivity {

		@AllArgsConstructor
		@NoArgsConstructor
		public static class AttributeFilter {
			public int attributeId;
			public Integer filterId;
		}

		public List<AttributeFilter> cost, material, time;

	}

	public int typeId;

	public ModifiedActivity copying, invention, manufacturing, reaction, researchMaterial, researchTime;

}
