package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BinaryOperator;

public class DoubleRWHolder extends NumberRWHolder<Double, DoubleHolder> implements DoubleHolder {

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
