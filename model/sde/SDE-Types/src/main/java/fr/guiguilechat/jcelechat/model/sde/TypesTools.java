package fr.guiguilechat.jcelechat.model.sde;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.attributes.MetaLevelOld;
import fr.guiguilechat.jcelechat.model.sde.attributes.TechLevel;

public class TypesTools {

	public static <T extends TypeRef<?>> Predicate<T> makePredicate(String filtersWithSpace) {
		Set<String> required = Stream.of(filtersWithSpace.split("\\s+")).map(String::toLowerCase)
				.filter(s -> !s.startsWith("-")).collect(Collectors.toSet());
		Set<String> forbidden = Stream.of(filtersWithSpace.split("\\s+")).map(String::toLowerCase)
				.filter(s -> s.startsWith("-")).map(s -> s.substring(1)).collect(Collectors.toSet());
		Predicate<T> pred = t -> {
			List<String> tokens = Stream
					.of(Stream.of(t.name().split(" ")), Stream.of(t.group().split(" ")), Stream.of(t.category().split(" ")))
					.flatMap(s -> s).map(String::toLowerCase).distinct().toList();
			for (String req : required) {
				if (tokens.stream().filter(tk -> tk.contains(req)).findAny().isEmpty()) {
					return false;
				}
			}
			for (String frb : forbidden) {
				if (tokens.stream().filter(tk -> tk.contains(frb)).findAny().isPresent()) {
					return false;
				}
			}
			return true;
		};
		return pred;
	}

	/**
	 * create a type predicate. Example : "module -t:2|m:0" will produce a predicate
	 * that matches types for which name, group of cat contains "module" AND (type
	 * is NOT 2 or meta is 0)
	 */
	public static <T extends EveType> Predicate<T> makeTypePredicate(String requestedFilter) {
		Predicate<T> ret = null;
		for (String tokenWithOr : requestedFilter.split("\\s+")) {
			Predicate<T> subPredicate = null;
			for (String token : tokenWithOr.split("|")) {
				Predicate<T> tokenPred = makeTokenPredicate(token);
				subPredicate = subPredicate == null ? tokenPred : subPredicate.or(tokenPred);
			}
			ret = ret == null ? subPredicate : ret.and(subPredicate);
		}

		return ret;
	}

	// all the remaining

	/**
	 * create a token-specific predicate.
	 * <ul>
	 * <li>leading "-" will negate the predicate</li>
	 * <li>format "m:X" expect meta level to be X</li>
	 * <li>format "t:X" expect tech level to be X</li>
	 * <li>anything else expects the token to appear, case insensitive, in the name,
	 * group name, or category name of the type</li>
	 * </ul>
	 *
	 * @param token a token without meaningful space of |. existing \ will be
	 *              removed.
	 * @return
	 */
	static <T extends EveType> Predicate<T> makeTokenPredicate(String token) {
		boolean negate = token.startsWith("-");
		if (negate) {
			token=token.substring(1);
		}
		token = token.toLowerCase();
		Predicate<T> ret = tokenPredicateMLevel(token);
		if (ret == null) {
			ret = tokenPredicateTLevel(token);
		}
		if (ret == null) {
			ret = tokenPredicateName(token);
		}

		if (ret != null && negate) {
			ret = ret.negate();
		}
		return ret;

	}

	static final Pattern METALEVEL_PATTERN = Pattern.compile("m:([0-9]+)");

	static <T extends EveType> Predicate<T> tokenPredicateMLevel(String tokenlc) {
		Matcher matcher = METALEVEL_PATTERN.matcher(tokenlc);
		if (matcher.matches()) {
			int requireLevel = Integer.parseInt(matcher.group(1));
			return t -> t.valueSet(MetaLevelOld.INSTANCE).intValue() == requireLevel;
		}
		return null;
	}

	static final Pattern TECHLEVEL_PATTERN = Pattern.compile("t:([0-9]+)");

	static <T extends EveType> Predicate<T> tokenPredicateTLevel(String tokenlc) {
		Matcher matcher = TECHLEVEL_PATTERN.matcher(tokenlc);
		if (matcher.matches()) {
			int requireLevel = Integer.parseInt(matcher.group(1));
			return t -> t.valueSet(TechLevel.INSTANCE).intValue() == requireLevel;
		}
		return null;
	}

	static <T extends EveType> Predicate<T> tokenPredicateName(String tokenlc) {
		return t -> (t.name.toLowerCase().contains(tokenlc)
				|| t.getGroup().getName().toLowerCase().contains(tokenlc)
				|| t.getCategory().getName().toLowerCase().contains(tokenlc));
	}

}
