package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformHolder;

public interface ListHolder<T> extends CollectionHolder<T, List<T>> {

	default Holder<T> at(int position, T absent) {
		return new TransformHolder<>(this, l -> l.size() > position ? l.get(position) : absent);
	}

}
