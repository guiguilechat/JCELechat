package fr.guiguilechat.jcelechat.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.jcelechat.model.sde.items.Item;
import fr.guiguilechat.jcelechat.model.sde.items.types.effects.AnimatedLights;
import fr.guiguilechat.jcelechat.model.sde.items.types.effects.LensFlares;
import fr.guiguilechat.jcelechat.model.sde.items.types.effects.ParticleSystems;

public abstract class Effects
    extends Item
{

    @Override
    public int getCategoryId() {
        return  53;
    }

    @Override
    public Class<?> getCategory() {
        return Effects.class;
    }

    public static Map<String, ? extends Effects> loadCategory() {
        return Stream.of(AnimatedLights.load(), LensFlares.load(), ParticleSystems.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
