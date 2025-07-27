package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BinaryOperator;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformHolder;

public class DoubleTransformHolder<U> extends TransformHolder<Double, U> implements DoubleHolder {

	public DoubleTransformHolder(Holder<U> source, Function<U, Double> transformer) {
		super(source, transformer);
	}

	@Override
	public DoubleHolder transform(DoubleHolder other, BinaryOperator<Double> transformer) {
		return new DoubleTransformPairHolder<>(this, other, transformer);
	}

}
