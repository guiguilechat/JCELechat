package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BinaryOperator;
import java.util.function.ToDoubleFunction;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformHolder;

public class DoubleTransformHolder<U> extends TransformHolder<Double, U> implements DoubleHolder {

	public DoubleTransformHolder(Holder<U> source, ToDoubleFunction<U> transformer) {
		super(source, transformer::applyAsDouble);
	}

	@Override
	public DoubleHolder transform(DoubleHolder other, BinaryOperator<Double> transformer) {
		return new DoubleTransformPairHolder<>(this, other, transformer);
	}

}
