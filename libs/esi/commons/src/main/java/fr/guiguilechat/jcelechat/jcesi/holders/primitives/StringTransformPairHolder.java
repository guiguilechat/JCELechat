package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import java.util.function.BiFunction;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformPairHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class StringTransformPairHolder<L, R> extends TransformPairHolder<String, L, R> implements StringHolder {

	public StringTransformPairHolder(Holder<L> leftSource, Holder<R> rightSource,
			BiFunction<L, R, String> transformer) {
		super(leftSource, rightSource, transformer);
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder isBlank = test(String::isBlank);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder isEmpty = test(String::isEmpty);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IntHolder length = mapInt(String::length);

}
