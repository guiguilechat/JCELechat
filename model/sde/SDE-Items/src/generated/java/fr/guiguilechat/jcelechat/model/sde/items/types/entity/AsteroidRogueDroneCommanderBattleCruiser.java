package fr.guiguilechat.jcelechat.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.jcelechat.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCommanderBattleCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneCommanderBattleCruiser.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneCommanderBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  843;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneCommanderBattleCruiser.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidRogueDroneCommanderBattleCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneCommanderBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidRogueDroneCommanderBattleCruiser> items;
    }
}
