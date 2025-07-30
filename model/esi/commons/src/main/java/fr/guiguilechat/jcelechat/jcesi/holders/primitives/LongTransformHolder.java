package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BinaryOperator;
import java.util.function.ToLongFunction;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformHolder;

public class LongTransformHolder<U> extends TransformHolder<Long, U> implements LongHolder {

	public LongTransformHolder(Holder<U> source, ToLongFunction<U> transformer) {
		super(source, transformer::applyAsLong);
	}

	@Override
	public LongHolder transform(LongHolder other, BinaryOperator<Long> transformer) {
		return new LongTransformPairHolder<>(this, other, transformer);
	}

}
