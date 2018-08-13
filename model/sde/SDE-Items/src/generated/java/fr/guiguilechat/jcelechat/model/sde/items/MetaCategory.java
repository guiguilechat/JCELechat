package fr.guiguilechat.jcelechat.model.sde.items;

import java.util.List;

public interface MetaCategory<T> {

    public List<MetaGroup<? extends T>> groups();
}
