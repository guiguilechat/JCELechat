package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;

public class LongTransformPairHolder<L, R> extends NumberTransformPairHolder<Long, LongHolder, L, R>
		implements LongHolder {

	public LongTransformPairHolder(Holder<L> leftSource, Holder<R> rightSource,
			BiFunction<L, R, Long> transformer) {
		super(leftSource, rightSource, transformer);
	}

	@Override
	public LongHolder transform(LongHolder other, BinaryOperator<Long> transformer) {
		return new LongTransformPairHolder<>(this, other, transformer);
	}

}
