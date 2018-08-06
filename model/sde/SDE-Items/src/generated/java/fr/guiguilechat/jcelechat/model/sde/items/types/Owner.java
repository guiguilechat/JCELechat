package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.owner.Character;

public abstract class Owner
    extends Item
{

    @Override
    public int getCategoryId() {
        return  1;
    }

    @Override
    public Class<?> getCategory() {
        return Owner.class;
    }

    public static Map<String, ? extends Owner> loadCategory() {
        return Stream.of(Character.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
