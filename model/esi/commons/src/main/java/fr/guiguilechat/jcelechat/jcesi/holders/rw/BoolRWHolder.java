package fr.guiguilechat.jcelechat.jcesi.holders.rw;

import fr.guiguilechat.jcelechat.jcesi.holders.BoolHolder;
import fr.guiguilechat.jcelechat.jcesi.holders.transform.BoolTransformHolder;
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
