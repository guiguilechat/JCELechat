package fr.guiguilechat.eveonline.model.sde.items.types;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import fr.guiguilechat.eveonline.model.sde.items.Item;
import fr.guiguilechat.eveonline.model.sde.items.types.lights.BoxLights;
import fr.guiguilechat.eveonline.model.sde.items.types.lights.PointLights;
import fr.guiguilechat.eveonline.model.sde.items.types.lights.SpotLights;

public abstract class Lights
    extends Item
{

    @Override
    public int getCategoryId() {
        return  54;
    }

    @Override
    public Class<?> getCategory() {
        return Lights.class;
    }

    public static Map<String, ? extends Lights> loadCategory() {
        return Stream.of(SpotLights.load(), PointLights.load(), BoxLights.load()).flatMap((m -> m.entrySet().stream())).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
