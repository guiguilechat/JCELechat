package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.List;
import java.util.function.BiFunction;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;

public class ListTransformPairHolder<T, L, R>
		extends CollectionTransformPairHolder<T, List<T>, L, R>
		implements ListHolder<T> {

	public ListTransformPairHolder(Holder<L> leftSource, Holder<R> rightSource, BiFunction<L, R, List<T>> transformer) {
		super(leftSource, rightSource, transformer);
	}

}
