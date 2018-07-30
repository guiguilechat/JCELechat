package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class WorldSpace
    extends Item
{

    @Override
    public int getCategoryId() {
        return  26;
    }

    @Override
    public Class<?> getCategory() {
        return WorldSpace.class;
    }

    public static Map<String, ? extends WorldSpace> loadCategory() {
        return Stream.of(fr.guiguilechat.jcelechat.model.sde.items.types.worldspace.WorldSpace.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
