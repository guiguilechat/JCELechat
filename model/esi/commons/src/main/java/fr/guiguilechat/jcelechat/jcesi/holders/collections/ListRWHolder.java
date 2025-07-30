package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.holders.common.RWHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.BoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.IntHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class ListRWHolder<T> extends RWHolder<List<T>> implements ListHolder<T> {

	public ListRWHolder() {
		super();
	}

	public ListRWHolder(List<T> newVal) {
		super(newVal);
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder isEmpty = test(List::isEmpty);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IntHolder size = mapInt(List::size);

}
