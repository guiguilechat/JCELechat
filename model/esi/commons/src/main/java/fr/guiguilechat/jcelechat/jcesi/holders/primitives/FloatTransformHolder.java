package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BinaryOperator;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.transform.TransformHolder;

public class FloatTransformHolder<U> extends TransformHolder<Float, U> implements FloatHolder {

	public FloatTransformHolder(Holder<U> source, Function<U, Float> transformer) {
		super(source, transformer);
	}

	@Override
	public FloatHolder transform(FloatHolder other, BinaryOperator<Float> transformer) {
		return new FloatTransformPairHolder<>(this, other, transformer);
	}

}
