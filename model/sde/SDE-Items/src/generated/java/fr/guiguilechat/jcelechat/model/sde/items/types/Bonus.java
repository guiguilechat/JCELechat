package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Bonus
    extends Item
{

    @Override
    public int getCategoryId() {
        return  14;
    }

    @Override
    public Class<?> getCategory() {
        return Bonus.class;
    }

    public static Map<String, ? extends Bonus> loadCategory() {
        return Collections.emptyMap();
    }
}
