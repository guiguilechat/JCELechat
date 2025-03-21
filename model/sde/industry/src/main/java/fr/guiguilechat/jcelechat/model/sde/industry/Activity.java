package fr.guiguilechat.jcelechat.model.sde.industry;

import java.time.Instant;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.exports.common.ArchiveManager;
import fr.guiguilechat.jcelechat.libs.exports.common.ListSerializer;
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
	private static final ListSerializer<Activity> storage = new ListSerializer<>("SDE/industry/activities.yaml",
			Activity.class);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final ArchiveManager<List<Activity>> archives = new ArchiveManager<>("SDE/industry/activities/",
			storage()::load);

	/**
	 * load the archived blueprint list for given date.
	 */
	public static List<Activity> load(Instant date) {
		return archives().dichoSearch(date, storage().load());
	}

	public static Activity of(int id, Instant date) {
		Activity ret = (date == null ? storage().load() : load(date)).stream()
				.filter(a -> a.activityId == id)
				.findAny().orElse(null);
		if (ret == null) {
			log.warn("unknown activity " + id);
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

	//
	// access
	//

}
