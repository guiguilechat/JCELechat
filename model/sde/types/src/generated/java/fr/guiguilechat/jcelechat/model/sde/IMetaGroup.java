package fr.guiguilechat.jcelechat.model.sde;

import java.util.Map;

public interface IMetaGroup<T extends EveType> {

    public int getGroupId();

    public IMetaCategory<? super T> category();

    public default Map<Integer, T> load() {
        return null;
    }

    public String getName();
}
