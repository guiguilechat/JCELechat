package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;

public abstract class System
    extends Item
{

    @Override
    public int getCategoryId() {
        return  0;
    }

    @Override
    public Class<?> getCategory() {
        return System.class;
    }

    public static Map<String, ? extends System> loadCategory() {
        return Stream.of(fr.guiguilechat.jcelechat.model.sde.items.types.system.System.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
