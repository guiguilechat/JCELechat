package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;

public class FloatTransformPairHolder<L, R> extends NumberTransformPairHolder<Float, FloatHolder, L, R>
		implements FloatHolder {

	public FloatTransformPairHolder(Holder<L> leftSource, Holder<R> rightSource,
			BiFunction<L, R, Float> transformer) {
		super(leftSource, rightSource, transformer);
	}

	@Override
	public FloatHolder transform(FloatHolder other, BinaryOperator<Float> transformer) {
		return new FloatTransformPairHolder<>(this, other, transformer);
	}

}
