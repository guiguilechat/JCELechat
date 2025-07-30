package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BinaryOperator;

public class LongRWHolder extends NumberRWHolder<Long, LongHolder> implements LongHolder {

	public LongRWHolder() {
		super();
	}

	public LongRWHolder(long newVal) {
		super(newVal);
	}

	@Override
	public LongHolder transform(LongHolder other, BinaryOperator<Long> transformer) {
		return new LongTransformPairHolder<>(this, other, transformer);
	}
}
