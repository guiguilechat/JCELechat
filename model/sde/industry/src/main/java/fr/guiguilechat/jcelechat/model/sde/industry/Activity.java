package fr.guiguilechat.jcelechat.model.sde.industry;

import java.time.Instant;
import java.util.Collections;
import java.util.HashSet;
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
public class Activity {

	//
	// storage
	//

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final MapIntSerializer<Activity> storage = new MapIntSerializer<>("SDE/industry/activities.yaml",
			Activity.class);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final ArchiveManager<Map<Integer, Activity>> archives = new ArchiveManager<>(
			"SDE/industry/activities/",
			storage()::load);

	/**
	 * load the archived list for given date.
	 */
	public static Map<Integer, Activity> load(Instant date) {
		return archives().dichoSearch(date, storage().load());
	}

	// only warn about missing ids once
	private static Set<Integer> missingIds = Collections.synchronizedSet(new HashSet<>());

	public static Activity of(int id, Instant date) {
		Activity ret = (date == null ? storage().load() : load(date)).get(id);
		if (ret == null && missingIds.add(id)) {
			log.warn("unknown id" + id);
		}
		return ret;
	}

	public static Activity of(int id) {
		return of(id, null);
	}

	//
	// structure
	//

	public int activityId;
	public String activityName;
	public String description;

}
