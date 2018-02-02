package fr.guiguilechat.eveonline.model.sde.items.types.entity;

import java.io.InputStreamReader;
import java.util.LinkedHashMap;
import fr.guiguilechat.eveonline.model.sde.items.types.Entity;
import org.yaml.snakeyaml.Yaml;

public class AsteroidGuristasBattleCruiser
    extends Entity
{
    public final static String RESOURCE_PATH = "SDE/items/entity/AsteroidGuristasBattleCruiser.yaml";
    private static LinkedHashMap<String, AsteroidGuristasBattleCruiser> cache = (null);

    @Override
    public int getGroupId() {
        return  580;
    }

    @Override
    public Class<?> getGroup() {
        return AsteroidGuristasBattleCruiser.class;
    }

    public static synchronized LinkedHashMap<String, AsteroidGuristasBattleCruiser> load() {
        if (cache == null) {
            try {
                cache = new Yaml().loadAs(new InputStreamReader(AsteroidGuristasBattleCruiser.class.getClassLoader().getResourceAsStream((RESOURCE_PATH))), (Container.class)).items;
            } catch (final Exception exception) {
                throw new UnsupportedOperationException("catch this", exception);
            }
        }
        return (cache);
    }

    private static class Container {
        public LinkedHashMap<String, AsteroidGuristasBattleCruiser> items;
    }
}
