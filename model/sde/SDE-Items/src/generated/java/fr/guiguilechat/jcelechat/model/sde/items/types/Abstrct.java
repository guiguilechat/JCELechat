package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Abstrct
    extends Item
{

    @Override
    public int getCategoryId() {
        return  29;
    }

    @Override
    public Class<?> getCategory() {
        return Abstrct.class;
    }

    public static Map<String, ? extends Abstrct> loadCategory() {
        return Collections.emptyMap();
    }
}
