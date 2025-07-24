package fr.guiguilechat.jcelechat.jcesi.holders.transform;

import java.util.function.BiPredicate;

import fr.guiguilechat.jcelechat.jcesi.holders.BoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class BoolTransformPairHolder<L, R> extends TransformPairHolder<Boolean, L, R> implements BoolHolder {

	public BoolTransformPairHolder(Holder<L> leftSource, Holder<R> rightSource, BiPredicate<L, R> transformer) {
		super(leftSource, rightSource, transformer::test);
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder not = BoolTransformHolder.not(this);

}
