package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class System
    extends Item
{

    @Override
    public int getCategoryId() {
        return  0;
    }

    @Override
    public Class<?> getCategory() {
        return System.class;
    }

    public static Map<String, ? extends System> loadCategory() {
        return Collections.emptyMap();
    }
}
