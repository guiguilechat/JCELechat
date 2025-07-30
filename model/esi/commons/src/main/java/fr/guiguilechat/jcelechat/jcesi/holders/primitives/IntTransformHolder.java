package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BinaryOperator;
import java.util.function.ToIntFunction;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformHolder;

public class IntTransformHolder<U> extends TransformHolder<Integer, U> implements IntHolder {

	public IntTransformHolder(Holder<U> source, ToIntFunction<U> transformer) {
		super(source, transformer::applyAsInt);
	}

	@Override
	public IntHolder transform(IntHolder other, BinaryOperator<Integer> transformer) {
		return new IntTransformPairHolder<>(this, other, transformer);
	}

}
