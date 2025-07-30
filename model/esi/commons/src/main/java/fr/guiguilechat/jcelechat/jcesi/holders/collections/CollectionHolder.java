package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.Collection;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.BoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.BoolTransformHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.BoolTransformPairHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.IntHolder;

public interface CollectionHolder<Internal, ColClass extends Collection<Internal>> extends Holder<ColClass> {

	IntHolder size();

	BoolHolder isEmpty();

	default BoolHolder contains(Internal item) {
		return new BoolTransformHolder<>(this, l -> l.contains(item));
	}

	default BoolHolder contains(Holder<Internal> item) {
		return new BoolTransformPairHolder<>(this, item, Collection::contains);
	}

//	ListHolder<Internal> sorted(Comparator<Internal> compare);

//	SetHolder distinct();

}
