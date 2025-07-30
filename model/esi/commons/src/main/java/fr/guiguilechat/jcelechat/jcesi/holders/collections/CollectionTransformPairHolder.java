package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.stream.Collectors;

import fr.guiguilechat.jcelechat.jcesi.holders.Holder;
import fr.guiguilechat.jcelechat.jcesi.holders.common.TransformPairHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.BoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.primitives.IntHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class CollectionTransformPairHolder<Internal, ColClass extends Collection<Internal>, L, R>
		extends TransformPairHolder<ColClass, L, R>
		implements CollectionHolder<Internal, ColClass> {

	public CollectionTransformPairHolder(Holder<L> leftSource, Holder<R> rightSource,
			BiFunction<L, R, ColClass> transformer) {
		super(leftSource, rightSource, transformer);
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
	private final SetHolder<Internal> distinct = this instanceof SetHolder s ? s
			: new SetTransformHolder<>(this,
					l -> l.stream().distinct().collect(Collectors.toSet()));

}
