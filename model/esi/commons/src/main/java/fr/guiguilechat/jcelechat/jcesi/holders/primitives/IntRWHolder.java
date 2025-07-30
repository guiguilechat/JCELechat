package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BinaryOperator;

public class IntRWHolder extends NumberRWHolder<Integer, IntHolder> implements IntHolder {

	public IntRWHolder() {
		super();
	}

	public IntRWHolder(int newVal) {
		super(newVal);
	}

	@Override
	public IntHolder transform(IntHolder other, BinaryOperator<Integer> transformer) {
		return new IntTransformPairHolder<>(this, other, transformer);
	}
}
