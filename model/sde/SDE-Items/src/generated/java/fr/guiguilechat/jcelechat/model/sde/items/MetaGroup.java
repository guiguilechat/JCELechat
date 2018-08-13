package fr.guiguilechat.jcelechat.model.sde.items;

import java.util.Collection;

public interface MetaGroup<T> {

    public MetaCategory<? super T> category();

    public default Collection<T> items() {
        return null;
    }

    public String getName();
}
