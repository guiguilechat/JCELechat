package fr.guiguilechat.jcelechat.libs.mer.killdump.filters;

import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.libs.mer.killdump.KDParser.KDEntry;
import fr.guiguilechat.jcelechat.model.sde.IMetaCategory;
import fr.guiguilechat.jcelechat.model.sde.IMetaGroup;

public class GroupFilter implements Predicate<KDEntry> {

	protected static Map<Integer, String> groupToName = makeGroupToName();

	@SuppressWarnings("unchecked")
	protected static Map<Integer, String> makeGroupToName() {
		Stream<IMetaGroup<?>> imgs = Stream.of(IMetaCategory.INSTANCES).flatMap(imc -> imc.groups().stream());
		return imgs.collect(Collectors.toMap(img -> img.getGroupId(), img -> img.getName()));
	}

	protected final int groupId;

	public GroupFilter(int groupId) {
		this.groupId = groupId;
	}

	@Override
	public boolean test(KDEntry t) {
		return t.getType() != null && t.getType().getGroupId() == groupId;
	}

	@Override
	public String toString() {
		return groupToName.getOrDefault(groupId, "g_" + groupId);
	}

	public static final GroupFilter EXHUMER = new GroupFilter(543);
	public static final GroupFilter FREIGHTER = new GroupFilter(513);
	public static final GroupFilter JUMP_FREIGHTER = new GroupFilter(902);
	public static final GroupFilter MINING_BARGE = new GroupFilter(463);
	public static final GroupFilter SHUTTLE = new GroupFilter(31);

}