package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.cells.PhysicalPortals;

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
        return Stream.of(PhysicalPortals.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
