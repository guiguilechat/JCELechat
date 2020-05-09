package fr.guiguilechat.jcelechat.model.sde;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface IMetaCategory<T extends EveType> {

    public int getCategoryId();

    public Collection<IMetaGroup<? extends T>> groups();

    public String getName();

    public default Map<String, T> load() {
        HashMap<String, T> ret = new HashMap<>();
        groups().stream().flatMap(img -> img.load().entrySet().stream()).forEach(e -> ret.put(e.getKey(), e.getValue()));
        return ret;
    }
}
