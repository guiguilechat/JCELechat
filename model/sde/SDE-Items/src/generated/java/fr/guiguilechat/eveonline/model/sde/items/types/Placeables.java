package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.placeables.Furniture;
import fr.guiguilechat.eveonline.model.sde.items.types.placeables.Generic;

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
        return Stream.of(Furniture.load(), Generic.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
