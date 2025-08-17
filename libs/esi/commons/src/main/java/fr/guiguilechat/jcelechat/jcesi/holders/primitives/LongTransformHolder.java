package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BinaryOperator;
import java.util.function.ToLongFunction;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;

public class LongTransformHolder<U> extends NumberTransformHolder<Long, LongHolder, U> implements LongHolder {

	public LongTransformHolder(Holder<U> source, ToLongFunction<U> transformer) {
		super(source, transformer::applyAsLong);
	}

	@Override
	public LongHolder transform(LongHolder other, BinaryOperator<Long> transformer) {
		return new LongTransformPairHolder<>(this, other, transformer);
	}

}
