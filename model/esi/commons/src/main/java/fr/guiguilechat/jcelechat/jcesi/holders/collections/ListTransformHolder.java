package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.List;
import java.util.function.Function;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.BoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.IntHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class ListTransformHolder<T, SourceType> extends TransformHolder<List<T>, SourceType> implements ListHolder<T> {

	public ListTransformHolder(Holder<SourceType> source, Function<SourceType, List<T>> transform) {
		super(source, transform);
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder isEmpty = test(List::isEmpty);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IntHolder size = mapInt(List::size);

}
