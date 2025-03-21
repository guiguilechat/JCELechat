package fr.guiguilechat.jcelechat.model.sde.industry;

import java.time.Instant;
import java.util.ArrayList;
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
@AllArgsConstructor
@NoArgsConstructor
public class InstallationType {

	//
	// storage
	//

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final MapIntSerializer<InstallationType> storage = new MapIntSerializer<>(
			"SDE/industry/installationtypes.yaml",
			InstallationType.class);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final ArchiveManager<Map<Integer, InstallationType>> archives = new ArchiveManager<>(
			"SDE/industry/installationtypes/",
			storage()::load);

	/**
	 * load the archived blueprint list for given date.
	 */
	public static Map<Integer, InstallationType> load(Instant date) {
		return archives().dichoSearch(date, storage().load());
	}

	// only warn about missing bp once
	private static Set<Integer> missingIds = Collections.synchronizedSet(new HashSet<>());

	public static InstallationType of(int id, Instant date) {
		InstallationType ret = (date == null ? storage().load() : load(date)).get(id);
		if (ret == null && !missingIds.add(id)) {
			log.warn("unknown id " + id);
		}
		return ret;
	}

	public static InstallationType of(int id) {
		return of(id, null);
	}

	//
	// structure
	//

	public int typeId;

	public ArrayList<Integer> assemblyLines = new ArrayList<>();

}
