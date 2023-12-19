package fr.guiguilechat.jcelechat.model.sde;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TypesTools {

	public static <T extends TypeRef<?>> Predicate<T> makePredicate(String filtersWithSpace) {
		Set<String> required = Stream.of(filtersWithSpace.split(" ")).map(String::toLowerCase)
				.filter(s -> !s.startsWith("-")).collect(Collectors.toSet());
		Set<String> forbidden = Stream.of(filtersWithSpace.split(" ")).map(String::toLowerCase)
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

}
