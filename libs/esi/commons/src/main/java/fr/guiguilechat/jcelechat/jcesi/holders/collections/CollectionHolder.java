package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.Collection;
import java.util.Comparator;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.BoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.BoolTransformHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.BoolTransformPairHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.IntHolder;

public interface CollectionHolder<Internal, ColClass extends Collection<Internal>> extends Holder<ColClass> {

	default BoolHolder contains(Internal item) {
		return new BoolTransformHolder<>(this, l -> l.contains(item));
	}

	default BoolHolder contains(Holder<Internal> item) {
		return new BoolTransformPairHolder<>(this, item, Collection::contains);
	}

	SetHolder<Internal> distinct();

	BoolHolder isEmpty();

	IntHolder size();

	default ListHolder<Internal> sorted(Comparator<Internal> compare) {
		return new ListTransformHolder<>(this, l -> l.stream().sorted(compare).toList());
	}

}
