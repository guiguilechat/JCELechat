package fr.guiguilechat.jcelechat.libs.exports.industry;

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
@NoArgsConstructor
@AllArgsConstructor
public class AssemblyLine {

	//
	// storage
	//

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final MapIntSerializer<AssemblyLine> storage = new MapIntSerializer<>(
			"SDE/industry/assemblylines.yaml",
			AssemblyLine.class);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private static final ArchiveManager<Map<Integer, AssemblyLine>> archives = new ArchiveManager<>(
			"SDE/industry/assemblylines/",
			storage()::load);

	/**
	 * load the archived list for given date.
	 */
	public static Map<Integer, AssemblyLine> load(Instant date) {
		return archives().dichoSearch(date, storage().load());
	}

	// only warn about missing ids once
	private static Set<Integer> missingIds = Collections.synchronizedSet(new HashSet<>());

	public static AssemblyLine of(int id, Instant date) {
		AssemblyLine ret = (date == null ? storage().load() : load(date)).get(id);
		if (ret == null && missingIds.add(id)) {
			log.warn("unknown id " + id);
		}
		return ret;
	}

	public static AssemblyLine of(int id) {
		return of(id, null);
	}

	//
	// structure
	//

	public static class CategoryDetail {

		public int categoryId;
		public float costMultiplier;
		public float materialMultiplier;
		public float timeMultiplier;

	}

	public static class GroupDetail {

		public int groupId;
		public float costMultiplier;
		public float materialMultiplier;
		public float timeMultiplier;

	}

	public static class TypeListDetail {

		public int typeListId;
		public float costMultiplier;
		public float materialMultiplier;
		public float timeMultiplier;

	}

	public int id;
	public int activity;
	public float baseCostMultiplier;
	public float baseMaterialMultiplier;
	public float baseTimeMultiplier;
	public String description;
	public ArrayList<TypeListDetail> typeListsDetails = new ArrayList<>();
	public ArrayList<CategoryDetail> categoriesDetails = new ArrayList<>();
	public ArrayList<GroupDetail> groupsDetails = new ArrayList<>();
	public String name;

}
