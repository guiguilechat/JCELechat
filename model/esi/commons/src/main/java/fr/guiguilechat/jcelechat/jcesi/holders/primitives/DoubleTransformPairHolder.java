package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformPairHolder;

public class DoubleTransformPairHolder<L, R> extends TransformPairHolder<Double, L, R> implements DoubleHolder {

	public DoubleTransformPairHolder(Holder<L> leftSource, Holder<R> rightSource,
			BiFunction<L, R, Double> transformer) {
		super(leftSource, rightSource, transformer);
	}

	@Override
	public DoubleHolder transform(DoubleHolder other, BinaryOperator<Double> transformer) {
		return new DoubleTransformPairHolder<>(this, other, transformer);
	}

}
