package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneCommanderFrigate
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneCommanderFrigate.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneCommanderFrigate> cache = (null);

    @Override
    public int getGroupId() {
        return  847;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneCommanderFrigate.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidRogueDroneCommanderFrigate> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneCommanderFrigate.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidRogueDroneCommanderFrigate> items;
    }
}
