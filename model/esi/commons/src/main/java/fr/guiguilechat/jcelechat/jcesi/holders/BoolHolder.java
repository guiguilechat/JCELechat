package fr.guiguilechat.jcelechat.jcesi.holders;

import fr.guiguilechat.jcelechat.jcesi.holders.transform.BoolTransformPairHolder;

public interface BoolHolder extends Holder<Boolean> {

	BoolHolder not();

	default BoolHolder and(BoolHolder other) {
		return new BoolTransformPairHolder<>(this, other, Boolean::logicalAnd);
	}

	default BoolHolder or(BoolHolder other) {
		return new BoolTransformPairHolder<>(this, other, Boolean::logicalOr);
	}

	default BoolHolder xor(BoolHolder other) {
		return new BoolTransformPairHolder<>(this, other, Boolean::logicalXor);
	}

	default <T> Holder<T> ifelse(T onTrue, T onFalse) {
		return map(b -> b ? onTrue : onFalse);
	}


}
