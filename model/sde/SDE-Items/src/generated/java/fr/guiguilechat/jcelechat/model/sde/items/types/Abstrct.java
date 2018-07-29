package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.abstrct.Audio;
import fr.guiguilechat.jcelechat.model.sde.items.types.abstrct.Decorations;
import fr.guiguilechat.jcelechat.model.sde.items.types.abstrct.Miscellaneous;
import fr.guiguilechat.jcelechat.model.sde.items.types.abstrct.PerceptionPoints;

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
        return Stream.of(Audio.load(), Decorations.load(), Miscellaneous.load(), PerceptionPoints.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
