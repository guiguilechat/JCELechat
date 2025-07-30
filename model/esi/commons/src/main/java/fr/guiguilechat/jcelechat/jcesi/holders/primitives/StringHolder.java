package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.collections.ListHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.collections.ListTransformPairHolder;

public interface StringHolder extends Holder<String> {

	default StringHolder concat(StringHolder next) {
		return new StringTransformPairHolder<>(this, next, String::concat);
	}

	default BoolHolder contains(CharSequence s) {
		return new BoolTransformHolder<>(this, str -> str.contains(s));
	}

	BoolHolder isBlank();

	BoolHolder isEmpty();

	IntHolder length();

	default ListHolder<String> split(String regex) {
		return mapList(s -> List.of(s.split(regex)));
	}

	default ListHolder<String> split(StringHolder regex) {
		return new ListTransformPairHolder<>(this, regex, (s, r) -> List.of(s.split(r)));
	}

	default BoolHolder matches(StringHolder regexp) {
		return new BoolTransformPairHolder<>(this, regexp, String::matches);
	}

}
