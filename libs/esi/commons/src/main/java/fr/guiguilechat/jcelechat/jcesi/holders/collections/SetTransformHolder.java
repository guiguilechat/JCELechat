package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.Set;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;

public class SetTransformHolder<T, SourceType>
		extends CollectionTransformHolder<T, Set<T>, SourceType>
		implements SetHolder<T> {

	public SetTransformHolder(Holder<SourceType> source, Function<SourceType, Set<T>> transform) {
		super(source, transform);
	}

}
