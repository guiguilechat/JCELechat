package fr.guiguilechat.jcelechat.model.formula.industry;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Activity {

	public static final double SCC_SURCHARGE_PC = 4.0;
	public static final double ALPHA_SURCHARGE_PC=0.25;

/**
 *
 * @param bpEIV EIV of the item. That is, the adjusted price of the mats required to build a 0/0 run.
 * @param nbRuns number of runs we start.
 * @param costIndexMult system cost index, base 1
 * @param locationManufCostMult multiplier of cost on the location, base 1
 * @param taxMult tax applied from the location, base 1
 * @param alphaclone true if the player is alpha
 * @param activityMult multiplier of EIV based on activity type.
 * @return
 */
	public static double installationCost(double bpEIV, int nbRuns, double costIndexMult, double locationManufCostMult,
			double taxMult, boolean alphaclone, double activityMult) {
		double raw= Math.ceil( nbRuns * bpEIV * activityMult );
		double indexBase=Math.ceil( raw*costIndexMult * locationManufCostMult);
		double taxBase=Math.ceil( raw*taxMult);
		double surchargeBase=Math.ceil(  raw*SCC_SURCHARGE_PC / 100);
		double alphaBase =Math.ceil(  raw * (alphaclone ? ALPHA_SURCHARGE_PC / 100 : 0));
//		System.err.println("raw="+raw+ "index="+indexBase+ "tax="+taxBase+" surcharge="+surchargeBase+" alpha="+alphaBase);
		return indexBase+taxBase+surchargeBase+alphaBase;
	}

	// hardcoded list of activities by CCP
	@RequiredArgsConstructor
	@Getter
	public enum Type {
		copying(5, "cp", "copy"),
		invention(8, "inv"),
		manufacturing(1, "manuf"),
		reaction(9, "reac", "reactions"),
		researchMaterial(4, "me", "research_material"),
		researchTime(3, "te", "research_time");

		Type(int id, String... aliases) {
			this(id, List.of(aliases));
		}

		private final int id;

		/** alternative names (ignorecase) by which they are known */
		@Getter(value = AccessLevel.PROTECTED)
		private final List<String> aliases;

		private static record AliasType(String alias, Type type) {
		}

		Stream<String> streamAliases() {
			return Stream.concat(Stream.of(name()), aliases.stream())
					.flatMap(Type::expandCases)
					.map(String::toLowerCase)
					.distinct();
		}

		@Getter(lazy = true)
		private final Collection<String> lowerNames = streamAliases().collect(Collectors.toSet());

		/**
		 * generate camel/underscore casing and source, upper/lower is ignored since
		 * they will all be lowercase() later.
		 */
		static Stream<String> expandCases(String source) {
			// match upper char following char, ABC will match "B" and "C" ; replace with _
			// followed by matched
			String undercase = Pattern.compile("(?<=[a-zA-Z])[A-Z]").matcher(source)
					.replaceAll(match -> "_" + match.group());
			// match underscore char following, replace it with matched
			String camelcase = Pattern.compile("(?<=[a-zA-Z])_[a-zA-Z]").matcher(source)
					.replaceAll(match -> match.group().substring(1));
			return Stream.of(source, undercase, camelcase);
		}

		@Getter(lazy = true)
		private final static Map<Integer, Type> byId = Stream.of(values())
				.collect(Collectors.toMap(Type::getId, t -> t));

		/** stream the lowercase aliases that can match this, associated to this */
		Stream<AliasType> aliasTypes() {
			return streamAliases()
					.map(s -> new AliasType(s, this));
		}

		@Getter(lazy = true)
		private final static Map<String, Type> byName = Stream.of(values())
				.flatMap(Type::aliasTypes)
				.collect(Collectors.toMap(AliasType::alias, AliasType::type));

		public static Type of(int id) {
			return getById().get(id);
		}

		public static Type of(String name) {
			return name == null ? null : getByName().get(name.toLowerCase());
		}
	}

}
