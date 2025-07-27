package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class StringTransformHolder<U> extends TransformHolder<String, U> implements StringHolder {

	public StringTransformHolder(Holder<U> source, Function<U, String> transformer) {
		super(source, transformer);
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IntHolder length = new IntTransformHolder<>(this, String::length);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder isEmpty = new BoolTransformHolder<>(this, String::isEmpty);

}
