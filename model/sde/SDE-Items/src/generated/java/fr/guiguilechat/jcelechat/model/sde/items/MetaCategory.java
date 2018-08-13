package fr.guiguilechat.jcelechat.model.sde.items;

import java.util.Collection;

public interface MetaCategory<T> {

    public Collection<MetaGroup<? extends T>> groups();

    public String getName();
}
