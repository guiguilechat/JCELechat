package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidRogueDroneHauler
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidRogueDroneHauler.yaml";
    private static LinkedHashMap<String, AsteroidRogueDroneHauler> cache = (null);

    @Override
    public int getGroupId() {
        return  760;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidRogueDroneHauler.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidRogueDroneHauler> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidRogueDroneHauler.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidRogueDroneHauler> items;
    }
}
