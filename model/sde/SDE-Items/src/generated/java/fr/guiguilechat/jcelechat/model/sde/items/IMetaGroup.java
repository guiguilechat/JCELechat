package fr.guiguilechat.jcelechat.model.sde.items;

import java.util.Map;

public interface IMetaGroup<T> {

    public abstract int getGroupId();

    public IMetaCategory<? super T> category();

    public default Map<String, T> load() {
        return null;
    }

    public String getName();
}
