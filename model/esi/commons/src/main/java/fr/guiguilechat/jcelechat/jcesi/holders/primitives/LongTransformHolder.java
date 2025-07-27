package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BinaryOperator;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformHolder;

public class LongTransformHolder<U> extends TransformHolder<Long, U> implements LongHolder {

	public LongTransformHolder(Holder<U> source, Function<U, Long> transformer) {
		super(source, transformer);
	}

	@Override
	public LongHolder transform(LongHolder other, BinaryOperator<Long> transformer) {
		return new LongTransformPairHolder<>(this, other, transformer);
	}

}
