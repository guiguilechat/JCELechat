package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.List;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;

public class ListTransformHolder<T, SourceType>
		extends CollectionTransformHolder<T, List<T>, SourceType>
		implements ListHolder<T> {

	public ListTransformHolder(Holder<SourceType> source, Function<SourceType, List<T>> transform) {
		super(source, transform);
	}

}
