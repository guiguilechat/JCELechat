package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import fr.guiguilechat.jcelechat.jcesi.holders.common.RWHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public abstract class NumberRWHolder<Contained extends Number, NumHolderItf extends NumberHolder<Contained, NumHolderItf>>
		extends RWHolder<Contained>
		implements NumberHolder<Contained, NumHolderItf> {

	NumberRWHolder() {
		super();
	}

	NumberRWHolder(Contained item) {
		super(item);
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
