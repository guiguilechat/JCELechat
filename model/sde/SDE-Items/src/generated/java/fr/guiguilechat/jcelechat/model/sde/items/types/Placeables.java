package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Placeables
    extends Item
{

    @Override
    public int getCategoryId() {
        return  49;
    }

    @Override
    public Class<?> getCategory() {
        return Placeables.class;
    }

    public static Map<String, ? extends Placeables> loadCategory() {
        return Collections.emptyMap();
    }
}
