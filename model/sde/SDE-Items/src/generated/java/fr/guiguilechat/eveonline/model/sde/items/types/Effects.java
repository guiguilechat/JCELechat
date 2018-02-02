package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.effects.AnimatedLights;
import fr.guiguilechat.eveonline.model.sde.items.types.effects.LensFlares;
import fr.guiguilechat.eveonline.model.sde.items.types.effects.ParticleSystems;

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
        return Stream.of(ParticleSystems.load(), AnimatedLights.load(), LensFlares.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
