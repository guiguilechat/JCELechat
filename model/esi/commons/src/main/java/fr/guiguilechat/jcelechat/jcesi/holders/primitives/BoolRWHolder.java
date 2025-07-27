package fr.guiguilechat.jcelechat.jcesi.holders.primitives;

import fr.guiguilechat.jcelechat.jcesi.holders.common.RWHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class BoolRWHolder extends RWHolder<Boolean> implements BoolHolder {

	public BoolRWHolder() {
		super();
	}

	public BoolRWHolder(boolean newVal) {
		super(newVal);
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder not = BoolTransformHolder.not(this);


}
