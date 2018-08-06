package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Station
    extends Item
{

    @Override
    public int getCategoryId() {
        return  3;
    }

    @Override
    public Class<?> getCategory() {
        return Station.class;
    }

    public static Map<String, ? extends Station> loadCategory() {
        return Collections.emptyMap();
    }
}
