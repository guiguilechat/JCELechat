package fr.guiguilechat.jcelechat.jcesi.holders.numbers;

import java.util.function.BinaryOperator;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;

/**
 * @param <Contained>    Type of Number stored
 * @param <NumHolderItf> the sub interface of NumberHolder for that Contained
 *                       type
 */
public interface NumberHolder<Contained extends Number, NumHolderItf extends NumberHolder<Contained, NumHolderItf>>
		extends Holder<Contained> {

	NumHolderItf transform(NumHolderItf other, BinaryOperator<Contained> transformer);

	NumHolderItf add(NumHolderItf other);

	NumHolderItf div(NumHolderItf other);

	NumHolderItf mult(NumHolderItf other);

	NumHolderItf sub(NumHolderItf other);

	default IntHolder toInt() {
		return new IntTransformHolder<>(this, Number::intValue);
	}
}
