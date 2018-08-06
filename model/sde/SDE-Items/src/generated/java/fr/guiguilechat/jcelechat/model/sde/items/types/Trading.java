package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Trading
    extends Item
{

    @Override
    public int getCategoryId() {
        return  10;
    }

    @Override
    public Class<?> getCategory() {
        return Trading.class;
    }

    public static Map<String, ? extends Trading> loadCategory() {
        return Collections.emptyMap();
    }
}
