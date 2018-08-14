package fr.guiguilechat.jcelechat.model.sde.items;

import java.util.Collection;
import java.util.Map;

public interface IMetaCategory<T> {

    public abstract int getCategoryId();

    public Collection<IMetaGroup<? extends T>> groups();

    public String getName();

    public Map<String, T> load();
}
