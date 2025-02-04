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

	/**
	 * make a predicate to match a type.
	 * <p>
	 * The filter argument is split by spaces, with the returned predicate checking
	 * each of those token
	 * <ul>
	 * <li>default is : token is contained ignore case in the type's name, group
	 * name, or category name. so "rig" filter will match all types of group
	 * frigate, any "X frigate" skill, any "rig" category item, etc.</li>
	 * <li>tokens starting with "-" are negated (so "-ship" will ignore all types
	 * that contain "ship" in their name, group name or cat name)</li>
	 * <li>tokens containing ":" refer to specific search depending on the prefix
	 * and suffix:
	 * <ul>
	 * <li>tn:X, gn:X, cn:X refer to the type, group or category name containing
	 * X</li>
	 * <li>t:X, m:X, p:X refer to techlevel, metalevel, or published of the type
	 * being X</li>
	 * </ul>
	 * </li>
	 * </ul>
	 * </p>
	 * <p>
	 * Implementation : <br />
	 * The predicate contains a list of filter token to contain, based on the
	 * filter's words.
	 * When checking a type, the predicate generates its list of type tokens, which
	 * include its name's, group's,
	 * and category's words, as well as specific token for group name ("g:X") and
	 * category name ("c:X"), metalevel("m:X") and techlevel("t:X").<br />
	 * For a given type, the predicate validates it iff each filter token is
	 * contained in at least one token of the type
	 * </p>
	 *
	 * @param <T>
	 * @param filtersWithSpace a string containing the tokens separated by spaces
	 * @return a predicate that returns true when a type matches all the existing
	 *           requested tokens. If parameter is null, return an accept-all
	 *           predicate
	 */
	public static <T extends TypeRef<?>> Predicate<T> makePredicate(String filtersWithSpace) {
		if (filtersWithSpace == null || filtersWithSpace.isBlank()) {
			return o->true;
		}
		Set<String> required = Stream.of(filtersWithSpace.split("\\s+")).map(String::toLowerCase)
				.filter(s -> !s.startsWith("-")).collect(Collectors.toSet());
		Set<String> forbidden = Stream.of(filtersWithSpace.split("\\s+")).map(String::toLowerCase)
				.filter(s -> s.startsWith("-")).map(s -> s.substring(1)).collect(Collectors.toSet());
		Predicate<T> pred = t -> {
			if (t.type() == null) {
				return false;
			}
			List<String> tokens = Stream.of(
					Stream.of(t.name().split(" ")),
					Stream.of(t.name().split(" ")).map(n -> "tn:" + n),
					Stream.of(t.group().split(" ")),
					Stream.of(t.group().split(" ")).map(n -> "gn:" + n),
					Stream.of(t.category().split(" ")),
					Stream.of(t.category().split(" ")).map(n -> "cn:" + n),
					Stream.of("p:" + t.type().published),
					Stream.of("t:" + TechLevel.INSTANCE.value(t.type()), "tl:" + TechLevel.INSTANCE.value(t.type()),
							"m:" + MetaLevelOld.INSTANCE.value(t.type()), "ml:" + MetaLevelOld.INSTANCE.value(t.type())
							)
					)
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
