package fr.guiguilechat.jcelechat.jcesi.holders.rw;

import fr.guiguilechat.jcelechat.jcesi.holders.BoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.transform.TransformBoolHolder;
import lombok.Getter;
import lombok.experimental.Accessors;

public class RWBoolHolder extends RWHolder<Boolean> implements BoolHolder {

	public RWBoolHolder() {
		super();
	}

	public RWBoolHolder(boolean newVal) {
		super(newVal);
	}

	@Getter(lazy = true)
	@Accessors(fluent = true)
	private final BoolHolder not = TransformBoolHolder.not(this);


}
