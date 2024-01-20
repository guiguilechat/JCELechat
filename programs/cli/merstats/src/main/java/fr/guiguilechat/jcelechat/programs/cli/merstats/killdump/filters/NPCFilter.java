package fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.filters;

import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.model.sde.load.fsd.EnpcCorporations;
import fr.guiguilechat.jcelechat.programs.cli.merstats.killdump.KDEntry;

public class NPCFilter {

	public static final Set<Integer> PLAYER_RACES=Set.of(1,2,4,8);

	public static boolean isNPCCorp(EnpcCorporations corp) {
		return corp.allowedMemberRaces == null
				|| corp.allowedMemberRaces.length == 0
				|| !PLAYER_RACES.contains(corp.allowedMemberRaces[0])
				|| corp.taxRate != 0.11 && corp.minimumJoinStanding != 0
				// || !"N".equals(corp.extent)
				// || !"T".equals(corp.size)
				;
	}

	public static final Set<Integer> NPC_GROUPIDS = Set.of(1876, 1924);
	public static final Set<Integer> NPC_CORPORATIONIDS = EnpcCorporations.load().entrySet().stream()
			.filter(e -> isNPCCorp(e.getValue())).map(Entry::getKey).collect(Collectors.toSet());

	public static final Predicate<KDEntry> NONPC = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return !NPC_GROUPIDS.contains(kde.getEType().groupID)
					&& !NPC_CORPORATIONIDS.contains(kde.killerCorporationID())
					&& !NPC_CORPORATIONIDS.contains(kde.victimCorporationID());
		}

		@Override
		public String toString() {
			return "NONPC";
		}
	};

	public static final Predicate<KDEntry> NPC = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return !NONPC.test(kde);
		}

		@Override
		public String toString() {
			return "NPC";
		}
	};

	public static final Predicate<KDEntry> ATKNPC = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return NPC_CORPORATIONIDS.contains(kde.killerCorporationID());
		}

		@Override
		public String toString() {
			return "ATKNPC";
		}
	};

	public static final Predicate<KDEntry> DEFNPC = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return NPC_CORPORATIONIDS.contains(kde.victimCorporationID());
		}

		@Override
		public String toString() {
			return "DEFNPC";
		}
	};

	public static final Predicate<KDEntry> ATKDEFNPC = new Predicate<>() {

		@Override
		public boolean test(KDEntry kde) {
			return ATKNPC.test(kde) && DEFNPC.test(kde);
		}

		@Override
		public String toString() {
			return "ATKDEFNPC";
		}
	};
}
