package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.reaction.FreedomPrograms;

public abstract class Reaction
    extends Item
{

    @Override
    public int getCategoryId() {
        return  24;
    }

    @Override
    public Class<?> getCategory() {
        return Reaction.class;
    }

    public static Map<String, ? extends Reaction> loadCategory() {
        return Stream.of(FreedomPrograms.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
