package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public abstract class NumberTransformHolder<Contained extends Number, NumHolderItf extends NumberHolder<Contained, NumHolderItf>, SourceType>
		extends TransformHolder<Contained, SourceType>
		implements NumberHolder<Contained, NumHolderItf> {

	public NumberTransformHolder(Holder<SourceType> source, Function<SourceType, Contained> transformer) {
		super(source, transformer);
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final DoubleHolder toDouble = this instanceof DoubleHolder h ? h
			: new DoubleTransformHolder<>(this, Number::doubleValue);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final FloatHolder toFloat = this instanceof FloatHolder h ? h
			: new FloatTransformHolder<>(this, Number::floatValue);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IntHolder toInt = this instanceof IntHolder h ? h : new IntTransformHolder<>(this, Number::intValue);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final LongHolder toLong = this instanceof LongHolder h ? h
			: new LongTransformHolder<>(this, Number::longValue);

}
