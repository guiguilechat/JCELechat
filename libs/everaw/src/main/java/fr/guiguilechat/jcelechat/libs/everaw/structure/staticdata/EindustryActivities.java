package fr.guiguilechat.jcelechat.libs.everaw.structure.staticdata;

import fr.guiguilechat.jcelechat.libs.everaw.parsers.sqlite.KeyValTimeLoader;
import lombok.Getter;

public class EindustryActivities {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<EindustryActivities> loader = new KeyValTimeLoader<>(
			EindustryActivities.class, "staticdata/industry_activities.static");

	public int activityID;
	public String description;
	public String activityName;

}
