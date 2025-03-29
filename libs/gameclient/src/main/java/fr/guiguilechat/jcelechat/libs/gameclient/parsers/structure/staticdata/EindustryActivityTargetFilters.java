package fr.guiguilechat.jcelechat.libs.gameclient.parsers.structure.staticdata;

import java.util.ArrayList;
import java.util.List;

import fr.guiguilechat.jcelechat.libs.gameclient.parsers.sqlite.KeyValTimeLoader;
import lombok.Getter;

public class EindustryActivityTargetFilters {

	@Getter(lazy = true)
	private static final KeyValTimeLoader<EindustryActivityTargetFilters> loader = new KeyValTimeLoader<>(
			EindustryActivityTargetFilters.class, "staticdata/industry_activity_target_filters.static");

	public List<Integer> categoryIDs = new ArrayList<>();
	public List<Integer> groupIDs = new ArrayList<>();
	public String name;

}
