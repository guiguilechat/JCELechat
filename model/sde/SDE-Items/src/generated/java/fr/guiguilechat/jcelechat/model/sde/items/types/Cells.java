package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Collections;
import java.util.Map;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class Cells
    extends Item
{

    @Override
    public int getCategoryId() {
        return  59;
    }

    @Override
    public Class<?> getCategory() {
        return Cells.class;
    }

    public static Map<String, ? extends Cells> loadCategory() {
        return Collections.emptyMap();
    }
}
