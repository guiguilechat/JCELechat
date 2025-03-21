package fr.guiguilechat.jcelechat.model.sde.industry;

import java.time.Instant;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.exports.common.ListSerializer;
import fr.guiguilechat.jcelechat.model.sde.industry.activity.ArchivedActivityList;
import fr.guiguilechat.jcelechat.model.sde.translate.ArchiveTools;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class Activity {

	// storage
	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final ListSerializer<Activity> yaml = new ListSerializer<>("SDE/industry/activities.yaml",
			Activity.class);

	//
	// structure
	//

	public int activityId;
	public String activityName;
	public String description;

	//
	// access
	//

	public static Activity of(int id, Instant date) {
		Activity ret = (date == null ? yaml().load() : load(date)).stream()
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

	@Getter(lazy = true)
	private static final List<ArchivedActivityList> archives = ArchivedActivityList.list();

	/**
	 * load the archived blueprint list for given date.
	 */
	public static List<Activity> load(Instant date) {
		return ArchiveTools.dichoSearch(getArchives(), date, yaml().load());
	}

}
