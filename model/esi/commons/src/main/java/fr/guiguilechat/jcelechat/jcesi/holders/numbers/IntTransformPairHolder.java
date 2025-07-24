package fr.guiguilechat.jcelechat.jcesi.holders.numbers;

import java.util.function.BiFunction;
import java.util.function.BinaryOperator;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.transform.TransformPairHolder;

public class IntTransformPairHolder<L, R> extends TransformPairHolder<Integer, L, R> implements IntHolder {

	public IntTransformPairHolder(Holder<L> leftSource, Holder<R> rightSource, BiFunction<L, R, Integer> transformer) {
		super(leftSource, rightSource, transformer);
	}

	@Override
	public IntHolder transform(IntHolder other, BinaryOperator<Integer> transformer) {
		return new IntTransformPairHolder<>(this, other, transformer);
	}

}
