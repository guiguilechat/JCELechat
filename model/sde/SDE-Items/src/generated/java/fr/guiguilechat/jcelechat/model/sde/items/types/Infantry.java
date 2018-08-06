package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Infantry
    extends Item
{

    @Override
    public int getCategoryId() {
        return  350001;
    }

    @Override
    public Class<?> getCategory() {
        return Infantry.class;
    }

    public static Map<String, ? extends Infantry> loadCategory() {
        return Collections.emptyMap();
    }
}
