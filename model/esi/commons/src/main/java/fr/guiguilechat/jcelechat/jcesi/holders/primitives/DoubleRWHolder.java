package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BinaryOperator;

import fr.guiguilechat.jcelechat.jcesi.holders.common.RWHolder;

public class DoubleRWHolder extends RWHolder<Double> implements DoubleHolder {

	public DoubleRWHolder() {
		super();
	}

	public DoubleRWHolder(double newVal) {
		super(newVal);
	}

	@Override
	public DoubleHolder transform(DoubleHolder other, BinaryOperator<Double> transformer) {
		return new DoubleTransformPairHolder<>(this, other, transformer);
	}
}
