package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BinaryOperator;

import fr.guiguilechat.jcelechat.jcesi.holders.rw.RWHolder;

public class FloatRWHolder extends RWHolder<Float> implements FloatHolder {

	public FloatRWHolder() {
		super();
	}

	public FloatRWHolder(float newVal) {
		super(newVal);
	}

	@Override
	public FloatHolder transform(FloatHolder other, BinaryOperator<Float> transformer) {
		return new FloatTransformPairHolder<>(this, other, transformer);
	}
}
