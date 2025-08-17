package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.Set;
import java.util.function.BiFunction;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;

public class SetTransformPairHolder<T, L, R>
		extends CollectionTransformPairHolder<T, Set<T>, L, R>
		implements SetHolder<T> {

	public SetTransformPairHolder(Holder<L> leftSource, Holder<R> rightSource, BiFunction<L, R, Set<T>> transformer) {
		super(leftSource, rightSource, transformer);
	}

}
