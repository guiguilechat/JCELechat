package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Effects
    extends Item
{

    @Override
    public int getCategoryId() {
        return  53;
    }

    @Override
    public Class<?> getCategory() {
        return Effects.class;
    }

    public static Map<String, ? extends Effects> loadCategory() {
        return Collections.emptyMap();
    }
}
