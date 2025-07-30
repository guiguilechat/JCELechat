package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.List;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformPairHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.IntHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.IntTransformHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.IntTransformPairHolder;

public interface ListHolder<T> extends CollectionHolder<T, List<T>> {

	/**
	 * @param absent return this if index out of bound or item is null
	 */
	default Holder<T> at(int position, T absent) {
		return new TransformHolder<>(this, l -> ListTools.at(l, position, absent));
	}

	/**
	 * @param absent return this if index out of bound or item is null
	 */
	default Holder<T> at(IntHolder position, T absent) {
		return new TransformPairHolder<>(this, position, (l, i) -> ListTools.at(l, i, absent));
	}

	default IntHolder indexOf(T item) {
		return new IntTransformHolder<>(this, l -> l.indexOf(item));
	}

	default IntHolder indexOf(Holder<T> item) {
		return new IntTransformPairHolder<>(this, item, List::indexOf);
	}

	/**
	 * @param fromIncluded if negative, use size()-fromIncluded instead
	 * @param toExcluded   if negative, use size()+1-toExcluded instead
	 */
	default ListHolder<T> subList(int fromIncluded, int toExcluded) {
		return new ListTransformHolder<>(this, l -> ListTools.subListCopy(l, fromIncluded, toExcluded));
	}

	default ListHolder<T> subListFrom(IntHolder from) {
		return new ListTransformPairHolder<>(this, from, ListTools::subListCopyFrom);
	}

	default ListHolder<T> subListTo(IntHolder from) {
		return new ListTransformPairHolder<>(this, from, ListTools::subListCopyTo);
	}

}
