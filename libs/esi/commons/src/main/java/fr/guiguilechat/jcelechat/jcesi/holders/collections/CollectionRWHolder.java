package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.Collection;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.holders.common.RWHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.BoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.IntHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class CollectionRWHolder<Internal, ColClass extends Collection<Internal>> extends RWHolder<ColClass>
		implements CollectionHolder<Internal, ColClass> {

	public CollectionRWHolder() {
		super();
	}

	public CollectionRWHolder(ColClass newVal) {
		super(newVal);
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder isEmpty = test(Collection::isEmpty);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IntHolder size = mapInt(Collection::size);

	@Getter(lazy = true)
	@SuppressWarnings("unchecked")
	@Accessors(fluent = true)
	private final SetHolder<Internal> distinct = (this instanceof SetHolder s) ? s
			: new SetTransformHolder<>(this,
					l -> l.stream().distinct().collect(Collectors.toSet()));

}
