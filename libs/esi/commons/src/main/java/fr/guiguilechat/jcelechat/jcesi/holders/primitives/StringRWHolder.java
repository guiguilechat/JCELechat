package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import fr.guiguilechat.jcelechat.jcesi.holders.common.RWHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class StringRWHolder extends RWHolder<String> implements StringHolder {

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder isBlank = test(String::isBlank);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder isEmpty = test(String::isEmpty);

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final IntHolder length = mapInt(String::length);

}
