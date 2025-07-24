package fr.guiguilechat.jcelechat.jcesi.holders.numbers;

import java.util.function.BinaryOperator;

import fr.guiguilechat.jcelechat.jcesi.holders.rw.RWHolder;

public class IntRWHolder extends RWHolder<Integer> implements IntHolder {

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
