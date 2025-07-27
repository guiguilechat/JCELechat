package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import fr.guiguilechat.jcelechat.jcesi.holders.common.RWHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class StringRWHolder extends RWHolder<String> implements StringHolder {

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IntHolder length = new IntTransformHolder<>(this, String::length);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder isEmpty = new BoolTransformHolder<>(this, String::isEmpty);

}
