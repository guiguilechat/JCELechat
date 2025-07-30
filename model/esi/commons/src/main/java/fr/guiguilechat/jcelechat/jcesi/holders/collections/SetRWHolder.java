package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.Set;

public class SetRWHolder<T> extends CollectionRWHolder<T, Set<T>> implements SetHolder<T> {

	public SetRWHolder() {
		super();
	}

	public SetRWHolder(Set<T> newVal) {
		super(newVal);
	}

}
