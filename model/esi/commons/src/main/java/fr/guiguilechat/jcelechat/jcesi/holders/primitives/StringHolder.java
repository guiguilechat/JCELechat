package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;

public interface StringHolder extends Holder<String> {

	default StringHolder concat(StringHolder next) {
		return new StringTransformPairHolder<>(this, next, String::concat);
	}

	default BoolHolder contains(CharSequence s) {
		return new BoolTransformHolder<>(this, str -> str.contains(s));
	}

	BoolHolder isEmpty();

	IntHolder length();

	default BoolHolder matches(StringHolder regexp) {
		return new BoolTransformPairHolder<>(this, regexp, String::matches);
	}

}
