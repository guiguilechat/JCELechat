package fr.guiguilechat.jcelechat.jcesi.holders.collections;

import java.util.List;

public class ListRWHolder<T> extends CollectionRWHolder<T, List<T>> implements ListHolder<T> {

	public ListRWHolder() {
		super();
	}

	public ListRWHolder(List<T> newVal) {
		super(newVal);
	}

}
